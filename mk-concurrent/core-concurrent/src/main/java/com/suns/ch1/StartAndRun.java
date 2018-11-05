/**
 * Project Name:core-concurrent <br>
 * File Name:StartAndRun.java <br>
 * Package Name:com.suns.ch1.safeend <br>
 * @author Administrator
 * Date:2018年11月3日下午8:52:50 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.ch1;
/**
 * ClassName: StartAndRun <br>
 * Description: 验收run和start方式
 * @author Administrator
 * @Date 2018年11月3日下午8:52:50 <br>
 * @version
 * @since JDK 1.6
 */
public class StartAndRun {

	private static class ThreadRun extends Thread{

		@Override
		public void run() {
			int i = 10;
			while(i>0) {
				System.out.println("i am "+Thread.currentThread().getName()+" and now i = "+(i--));
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadRun threadRun = new ThreadRun();
		threadRun.setName("threadRun");
		threadRun.run();//不会新启动一个线程，相当于调用普通方法
		threadRun.start();//新启动线程
		
		threadRun.setPriority(1);//线程优先级
	}
}

	