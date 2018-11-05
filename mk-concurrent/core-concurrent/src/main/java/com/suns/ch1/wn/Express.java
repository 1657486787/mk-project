/**
 * Project Name:core-concurrent <br>
 * File Name:Express.java <br>
 * Package Name:com.suns.ch1.wn <br>
 * @author Administrator
 * Date:2018年11月4日上午10:11:30 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.ch1.wn;
/**
 * ClassName: Express <br>
 * Description: 快递实体，公里数，位置
 * @author Administrator
 * @Date 2018年11月4日上午10:11:30 <br>
 * @version
 * @since JDK 1.6
 */
public class Express {

	public static final String CITY = "shanghai";
	private int km;
	private String site;
	
	public Express(int km, String site) {
		super();
		this.km = km;
		this.site = site;
	}
	
	public synchronized void changeKm() {
		this.km = 101;
		notifyAll();
//		notify();
	}
	
	public void changeSite() {
		this.site = "Beijing";
		notifyAll();
	}

	public synchronized void waitKm() {
		//当km<=100时，就等待
		while(km <= 100) {
			try {
				System.out.println(Thread.currentThread().getName() +" wait.");
				wait();
				System.out.println(Thread.currentThread().getName() +" is be notifyed.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(Thread.currentThread().getName() +" km 改变，接下来做自己的业务如change db");
	}
	
	public synchronized void waitSite() {
		while(CITY.equals(site)) {
			try {
				System.out.println(Thread.currentThread().getName() +" wait.");
				wait();
				System.out.println(Thread.currentThread().getName() +" is be notifyed.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() +" site 改变，接下来做自己的业务如change db");
	}
	
	
}

	