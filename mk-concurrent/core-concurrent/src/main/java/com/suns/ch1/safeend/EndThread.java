/**
 * Project Name:core-concurrent <br>
 * File Name:EndThread.java <br>
 * Package Name:com.suns.ch1.safeend <br>
 * @author Administrator
 * Date:2018年11月3日下午8:01:43 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.ch1.safeend;
/**
 * ClassName: EndThread <br>
 * Description: 如何正确安全终止线程：使用interrupt方法
 * 1.在主线程调用子线程的interrupt()方法
 * 2.在子线程中调用isInterrupted()方法可以获取标识位
 * java中的线程是协作式的：使用interrupt()中断线程,线程不会马上停止，而是将标识位标识为true，如果线程中不判断使用该标识位，那么是没用的，如while(true)。
 * 使用isInterrupted()方法可以获取标识位
 *  stop(),resume(),suspend会占用资源，不要采用这些方法
 * @author Administrator
 * @Date 2018年11月3日下午8:01:43 <br>
 * @version
 * @since JDK 1.6
 */
public class EndThread {

	
	private static class MyThread extends Thread{
		
		public MyThread(String threadName) {
			super(threadName);
		}

		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
//			while(true) { //调用while(true)，相当于不理会interrupt标识位
			while(!isInterrupted()) {
				System.out.println(threadName +" is run.");
			}
			System.out.println(threadName +" interrupt flag:"+isInterrupted());
			
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		MyThread myThread = new MyThread("endThread");
		myThread.start();
		Thread.sleep(10);
		myThread.interrupt();
	}
}

	