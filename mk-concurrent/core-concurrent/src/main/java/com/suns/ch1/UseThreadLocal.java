/**
 * Project Name:core-concurrent <br>
 * File Name:UseThreadLocal.java <br>
 * Package Name:com.suns.ch1 <br>
 * @author Administrator
 * Date:2018年11月4日上午9:02:12 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.ch1;
/**
 * ClassName: UseThreadLocal <br>
 * Description: ThreadLocal
 * @author Administrator
 * @Date 2018年11月4日上午9:02:12 <br>
 * @version
 * @since JDK 1.6
 */
public class UseThreadLocal {

	//ThreadLocal:可以理解为map<thread,integer>
	static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {

		@Override
		protected Integer initialValue() {
			return 1;
		}
		
	};
	
	private static class UserRun implements Runnable{

		private int id;
		public UserRun(int id) {
			this.id = id;
		}
		
		public void run() {
			String threadName = Thread.currentThread().getName();
			int index = threadLocal.get();
			System.out.println(threadName + " threadLocal:"+index);
			index += id;
			threadLocal.set(index);
			System.out.println(threadName + " threadLocal:"+threadLocal.get());
		}
		
	}
	
	private static void startThreadArray() {
		Thread [] array = new Thread[4];
		for(int i=0;i<array.length;i++) {
			array[i] = new Thread(new UserRun(i));
		}
		for(int i=0;i<array.length;i++) {
			array[i].start();
		}
	}
	
	public static void main(String[] args) {
		startThreadArray();
	}


	
}

	