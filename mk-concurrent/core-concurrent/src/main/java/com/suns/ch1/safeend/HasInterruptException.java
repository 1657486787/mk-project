/**
 * Project Name:core-concurrent <br>
 * File Name:HasInterruptException.java <br>
 * Package Name:com.suns.ch1.safeend <br>
 * @author Administrator
 * Date:2018年11月3日下午8:18:24 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.ch1.safeend;


/**
 * ClassName: HasInterruptException <br>
 * Description:  如何正确安全终止线程：使用interrupt方法
 * 1.在主线程调用子线程的interrupt()方法
 * 2.在子线程中调用isInterrupted()方法可以获取标识位
 * 3.调用会抛出InterruptedException的方法时，标识位会变为false。需要在捕获异常的时候，再调用一次interrupt()方法。
 * @author Administrator
 * @Date 2018年11月3日下午8:18:24 <br>
 * @version
 * @since JDK 1.6
 */
public class HasInterruptException {

	
private static class MyThread extends Thread{
		
		public MyThread(String threadName) {
			super(threadName);
		}

		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			while(!isInterrupted()) {
				System.out.println(threadName +" is run.");
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.out.println(threadName +" interrupt flag:"+isInterrupted());
					e.printStackTrace();
					System.out.println("调用会抛出InterruptedException的方法时，标识位会变为false。需要在捕获异常的时候，再调用一次interrupt()方法。");
					interrupt();
				}
			}
			System.out.println(threadName +" interrupt flag:"+isInterrupted());
			
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		MyThread myThread = new MyThread("endThread");
		myThread.start();
		Thread.sleep(1000);
		myThread.interrupt();
	}
}

	