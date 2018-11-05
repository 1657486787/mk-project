/**
 * Project Name:core-concurrent <br>
 * File Name:DaemonThread.java <br>
 * Package Name:com.suns.ch1 <br>
 * @author Administrator
 * Date:2018年11月3日下午9:08:52 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.ch1;

/**
 * ClassName: DaemonThread <br>
 * Description: 演示守护线程：1.与主线程共死，2.不能保证fianlly一定执行
 * @author Administrator
 * @Date 2018年11月3日下午9:08:52 <br>
 * @version
 * @since JDK 1.6
 */
public class DaemonThread {
	
	private static class MyThread extends Thread{
		
		public MyThread(String threadName) {
			super(threadName);
		}

		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			try {
				while (!isInterrupted()) {
					System.out.println(threadName + " is run.");
				}
				System.out.println(threadName + " interrupt flag:" + isInterrupted());
			} finally {
				System.out.println(threadName+" finally...........");
			}
			
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		//1.如果子线程不设置为守护线程daemon，那么while不会停止
//		method1();
		
		//2.如果子线程设置为守护线程daemon，那么while会跟随这主线程 停止而停止
//		method2();
		
		//3.如果子线程不设置为守护线程daemon，那么finally中的语句会执行
//		method3();
		
		//4.如果子线程设置为守护线程daemon，那么finally中的语句不能执行一定执行
		method4();
	}

	private static void method1() throws InterruptedException {
		MyThread myThread = new MyThread("endThread");
		myThread.start();
		Thread.sleep(1000);
	}
	
	private static void method2() throws InterruptedException {
		MyThread myThread = new MyThread("endThread");
		myThread.setDaemon(true);//要在start方法前设置才有用
		myThread.start();
		Thread.sleep(1000);
	}
	
	private static void method3() throws InterruptedException {
		MyThread myThread = new MyThread("endThread");
		myThread.start();
		Thread.sleep(500);
		myThread.interrupt();
	}
	
	private static void method4() throws InterruptedException {
		MyThread myThread = new MyThread("endThread");
		myThread.setDaemon(true);//要在start方法前设置才有用
		myThread.start();
		Thread.sleep(500);
		myThread.interrupt();
	}
}

	