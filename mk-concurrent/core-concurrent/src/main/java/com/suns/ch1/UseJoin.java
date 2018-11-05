/**
 * Project Name:core-concurrent <br>
 * File Name:UseJoin.java <br>
 * Package Name:com.suns.ch1 <br>
 * @author Administrator
 * Date:2018年11月4日下午4:23:38 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.ch1;
/**
 * ClassName: UseJoin <br>
 * Description: 测试join方法
 * 如a线程调用b线程的join方法，那么a线程会等b线程执行完了以后，再继续执行a线程的方法
 * 就比我（a线程）如在食堂打饭，请一个美女（b线程）过来插队，那么就要等这个美女（b线程）打完饭，我（a线程）才能继续打饭
 * @author Administrator
 * @Date 2018年11月4日下午4:23:38 <br>
 * @version
 * @since JDK 1.6
 */
public class UseJoin {

	private static class UserRun implements Runnable{

		private Thread previous;
		public UserRun(Thread previous) {
			this.previous = previous;
		}
		
		public void run() {
			try {
				System.out.println(previous.getName()+" will before "+Thread.currentThread().getName());
				previous.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName() +" stop.");
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread previous = Thread.currentThread();//前一个前程，设置最开始的线程为主线程
		System.out.println(previous.getName());
		for(int i=0;i<10;i++) {
			Thread current = new Thread(new UserRun(previous));
			current.start();
			previous = current;
		}
		
		Thread.sleep(2000);//让主线程休息，在他之后的线程必须要等主线程休息玩之后才能继续
		System.out.println(Thread.currentThread().getName() +" stop.");
	}
	
}

	