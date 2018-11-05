/**
 * Project Name:core-concurrent <br>
 * File Name:DBPoolTest.java <br>
 * Package Name:com.suns.ch1.pool <br>
 * @author Administrator
 * Date:2018年11月4日上午11:31:19 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.ch1.pool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: DBPoolTest <br>
 * Description: 数据库连接池测试 获取连接和释放连接
 * 等待超时模式实现一个连接池
	假设  等待时间时长为T，当前时间now+T以后超时
	
	long  overtime = now+T;
	long remain = T;//等待的持续时间
	while(result不满足条件&& remain>0){
		wait(remain);
		remain = overtime – now;//等待剩下的持续时间
	}
	return result;
 * @author Administrator
 * @Date 2018年11月4日上午11:31:19 <br>
 * @version
 * @since JDK 1.6
 */
public class DBPoolTest {

	static DBPool pool = new DBPool(10);
	static CountDownLatch countDownLatch = null;
	
	private static class Worker implements Runnable{

		private int getConnCount;
		private AtomicInteger succCnt;
		private AtomicInteger failCnt;
		
		public Worker(int getConnCount, AtomicInteger succCnt, AtomicInteger failCnt) {
			this.getConnCount = getConnCount;
			this.succCnt = succCnt;
			this.failCnt = failCnt;
		}

		public void run() {
			while(getConnCount>0) {
				try {
					Connection conn = pool.getConn(1000);
					if(null != conn) {
						try {
							//模拟业务操作
							conn.createStatement();
							conn.commit();
						} finally {
							pool.releaseConn(conn);
							System.out.println(Thread.currentThread().getName()
									+" 成功获取连接"+succCnt.incrementAndGet());
						}
					}else {
						System.out.println(Thread.currentThread().getName()
								+" 没有获取连接"+failCnt.incrementAndGet());
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(Thread.currentThread().getName()
							+" 获取连接异常"+failCnt.incrementAndGet());
				} finally {
					getConnCount --;
				}
			}
			countDownLatch.countDown();
			System.out.println("countDownLatch:"+countDownLatch.getCount());
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		int threadCount = 50;//线程个数
		int getConnCount = 20;//每个线程获取连接次数
		AtomicInteger succCnt = new AtomicInteger(0);//成功获取连接个数
		AtomicInteger failCnt = new AtomicInteger(0);//没有获取连接个数
		
		countDownLatch = new CountDownLatch(threadCount);
		
		for(int i=0;i<threadCount;i++) {
			new Thread(new Worker(getConnCount,succCnt,failCnt),"worker_"+i).start();
		}
		countDownLatch.await();
		System.out.println("总连接个数:"+(threadCount*getConnCount));
		System.out.println("成功获取连接个数:"+succCnt);
		System.out.println("没有获取连接个数:"+failCnt);
	}
}

	