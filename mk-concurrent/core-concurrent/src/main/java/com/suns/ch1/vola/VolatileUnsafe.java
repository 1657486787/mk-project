/**
 * Project Name:core-concurrent <br>
 * File Name:VolatileUnsafe.java <br>
 * Package Name:com.suns.ch1.vola <br>
 * @author Administrator
 * Date:2018年11月3日下午10:38:09 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.ch1.vola;

import com.suns.utils.SleepTools;

/**
 * ClassName: VolatileUnsafe <br>
 * Description: 演示volatile无法保证原子性，就是说不是线程安全的
 * 每个线程中的变量都有自己的工作内存，还有一块是主内存，使用volatile关键字修饰的变量，每次获取变量值的时候，会将工作内存的值失效，从主内存中获取并更新到工作内存中
 * 适用于只有一个线程写，多个线程读的场景
 * 
 * 可以通过加synchronized（this）块来解决
 * @author Administrator
 * @Date 2018年11月3日下午10:38:09 <br>
 * @version
 * @since JDK 1.6
 */
public class VolatileUnsafe {

	private static class VolatileVar implements Runnable{

		private volatile int a = 0;
		public void run() {
//			String threadName = Thread.currentThread().getName();
//			a++;
//			System.out.println(threadName+" a="+a);
//			SleepTools.ms(10);
//			a=a+2;
//			System.out.println(threadName+" a="+a);
			
			synchronized (this) {
				String threadName = Thread.currentThread().getName();
				a++;
				System.out.println(threadName+" a="+a);
				SleepTools.ms(10);
				a=a+2;
				System.out.println(threadName+" a="+a);
			}
		}
	}
	
	public static void main(String[] args) {
		VolatileVar volatileVar = new VolatileVar();
		Thread thread1 = new Thread(volatileVar);
		Thread thread2 = new Thread(volatileVar);
		Thread thread3 = new Thread(volatileVar);
		Thread thread4 = new Thread(volatileVar);
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}
}

	