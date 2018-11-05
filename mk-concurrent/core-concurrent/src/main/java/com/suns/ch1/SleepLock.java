/**
 * Project Name:core-concurrent <br>
 * File Name:SleepLock.java <br>
 * Package Name:com.suns.ch1 <br>
 * @author Administrator
 * Date:2018年11月4日下午4:34:51 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.ch1;


/**
 * ClassName: SleepLock <br>
 * Description: 演示sleep,调用sleep的时候，拥有的锁不会释放
 * @author Administrator
 * @Date 2018年11月4日下午4:34:51 <br>
 * @version
 * @since JDK 1.6
 */
public class SleepLock {
	
	private static Object lock = new Object();
	
	public static class ThreadSleep implements Runnable{

		public void run() {
			synchronized (lock) {
				System.out.println(Thread.currentThread().getName() +" get lock ");
				try {
					System.out.println(Thread.currentThread().getName() +" get lock sleep start...");
					Thread.sleep(2000);
					System.out.println(Thread.currentThread().getName() +" get lock sleep end...");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static class ThreadNotSleep implements Runnable{

		public void run() {
			synchronized (lock) {
				System.out.println(Thread.currentThread().getName() +" get lock ");
			}
			
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new ThreadSleep(), "threadSleep").start();
		System.out.println(Thread.currentThread().getName() +" sleep start.");
		Thread.sleep(1000); //主线程休眠，为了保证ThreadSleep线程先执行获得锁
		System.out.println(Thread.currentThread().getName() +" sleep end.");
		new Thread(new ThreadNotSleep(), "threadNotSleep").start();
		
	}

}

	