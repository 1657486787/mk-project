/**
 * Project Name:core-concurrent <br>
 * File Name:EndRunnable.java <br>
 * Package Name:com.suns.ch1.safeend <br>
 * @author Administrator
 * Date:2018年11月3日下午8:13:58 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.ch1.safeend;
/**
 * ClassName: EndRunnable <br>
 * Description: 如何正确安全终止线程：使用interrupt方法
 * @author Administrator
 * @Date 2018年11月3日下午8:13:58 <br>
 * @version
 * @since JDK 1.6
 */

public class EndRunnable {

	private static class MyRunnable implements Runnable{

		public void run() {
			String threadName = Thread.currentThread().getName();
			while(!Thread.currentThread().isInterrupted()) {
				System.out.println(threadName+" is run.");
			}
			System.out.println(threadName +" interrupte flag is "+Thread.currentThread().isInterrupted());
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new MyRunnable(), "endRunnable");
		thread.start();
		Thread.sleep(10);
		thread.interrupt();
	}
	
}

	