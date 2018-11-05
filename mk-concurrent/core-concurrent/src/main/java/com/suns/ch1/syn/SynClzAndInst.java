/**
 * Project Name:core-concurrent <br>
 * File Name:SynClzAndInst.java <br>
 * Package Name:com.suns.ch1.syn <br>
 * @author Administrator
 * Date:2018年11月3日下午9:47:46 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.ch1.syn;

import com.suns.utils.SleepTools;

/**
 * ClassName: SynClzAndInst <br>
 * Description: 演示Synchronized 内置锁
 * 1.对象锁，在方法上加synchronized ，或 synchronized（this），对象锁锁的是对象的实例。特别要注意如果对象的实例不同，那么是锁不住的
 * 2.类锁，在方法上加static synchronized关键字，类锁锁的是类的class,在整个虚拟机中有且只有一个
 * @author Administrator
 * @Date 2018年11月3日下午9:47:46 <br>
 * @version
 * @since JDK 1.6
 */
public class SynClzAndInst {

	
	private static class SynClz implements Runnable{

		public void run() {
			System.out.println(Thread.currentThread().getName()+"----SynClz is before.");
			synClz();
		}
	}
	
	private static class SynInstance implements Runnable{

		private SynClzAndInst synClzAndInst;
		public SynInstance(SynClzAndInst synClzAndInst) {
			this.synClzAndInst = synClzAndInst;
		}
		
		public void run() {
			System.out.println(Thread.currentThread().getName()+"----SynInstance is before.");
//			synClzAndInst.synInstance();
			synClzAndInst.synInstance2();
		}
	}
	
	/**
	 * synClz:类锁. 在方法上加static synchroinzed <br>
	 * @author Administrator
	 * @Date 2018年11月3日下午10:20:02 <br>
	 */
	private static synchronized void synClz() {
		SleepTools.second(1);
		System.out.println(Thread.currentThread().getName()+"----synClz is run.");
		SleepTools.second(1);
		System.out.println(Thread.currentThread().getName()+"----synClz is end.");
	}
	
	/**
	 * synClz:对象锁. 在方法上加synchroinzed <br>
	 * @author Administrator
	 * @Date 2018年11月3日下午10:20:02 <br>
	 */
	public synchronized void synInstance() {
		SleepTools.second(1);
		System.out.println(Thread.currentThread().getName()+"----synInstance is run.");
		SleepTools.second(1);
		System.out.println(Thread.currentThread().getName()+"----synInstance is end.");
	}
	
	/**
	 * synClz:对象锁. synchroinzed(this) 块 <br>
	 * @author Administrator
	 * @Date 2018年11月3日下午10:20:02 <br>
	 */
	public void synInstance2() {
		synchronized (this) {
			SleepTools.second(1);
			System.out.println(Thread.currentThread().getName()+"----synInstance is run.");
			SleepTools.second(1);
			System.out.println(Thread.currentThread().getName()+"----synInstance is end.");
		}
	}

	public static void main(String[] args) {
		//1.对象锁
		method1();
		
		//2.类锁
//		method2();
	}

	private static void method1() {
		//锁同一个对象，就是说锁的是同一个对象的实例
		SynClzAndInst synClzAndInst = new SynClzAndInst();
		new Thread(new SynInstance(synClzAndInst),"synInstance").start();
		new Thread(new SynInstance(synClzAndInst),"synInstance2").start();
		
		//锁不同对象，就是说锁的是两个对象的实例，那么使用synchronized方法是没用的，特别要注意，不能犯这个错误
//		SynClzAndInst synClzAndInst = new SynClzAndInst();
//		SynClzAndInst synClzAndInst2 = new SynClzAndInst();
//		new Thread(new SynInstance(synClzAndInst),"synInstance").start();
//		new Thread(new SynInstance(synClzAndInst2),"synInstance2").start();
	}

	private static void method2() {
		SynClz synClz = new SynClz();
		SynClz synClz2 = new SynClz();
		new Thread(synClz,"synClz").start();
		new Thread(synClz2,"synClz2").start();
	}
}

	