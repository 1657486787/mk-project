/**
 * Project Name:core-concurrent <br>
 * File Name:TestWN.java <br>
 * Package Name:com.suns.ch1.wn <br>
 * @author Administrator
 * Date:2018年11月4日上午10:11:59 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.ch1.wn;

/**
 * ClassName: TestWN <br>
 * Description: 演示wait,notify/notifyAll 
 * notify和notifyAll应该用谁？应该尽量使用notifyAll，使用notify因为有可能发生信号丢失的的情况 (使用notifyAll而不使用notify，应为notify只唤醒在该对象上等待的线程，由于不会notify不会传递，这样等待在该对象的其它线程不会被唤醒)
 * 等待和通知的标准范式：
 * 1.等待方：
 * 获取对象锁
 * 循环判断条件是否满足，如果不满足继续等待wait
 * 如果满足，则进行自己的业务操作
 * 2.通知方：
 * 获取对象锁
 * 改变条件
 * 调用notifyAll，通知等待该对象上的所有线程
 * @author Administrator
 * @Date 2018年11月4日上午10:11:59 <br>
 * @version
 * @since JDK 1.6
 */
public class TestWN {

	private static class KmThread extends Thread{
		
		private Express express;
		public KmThread(Express express) {
			this.express = express;
		}

		public void run() {
			express.waitKm();
		}
	}
	
private static class SiteThread extends Thread{
		
		private Express express;
		public SiteThread(Express express) {
			this.express = express;
		}

		public void run() {
			express.waitSite();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Express express = new Express(0,Express.CITY);
		
		for(int i=0;i<3;i++) {
			new SiteThread(express).start();
		}
		
		for(int i=0;i<3;i++) {
			new KmThread(express).start();
		}
		
		Thread.sleep(1000);
		express.changeKm();
		
	}
}

	