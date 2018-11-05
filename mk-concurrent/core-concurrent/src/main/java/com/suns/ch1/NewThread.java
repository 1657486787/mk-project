package com.suns.ch1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 演示线程创建的几种方式
 * 1.继承Thread
 * 2.实现Runnable接口
 * 3.实现Callable接口
 * @author Administrator
 *
 */
public class NewThread {

	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		NewThread newThread = new NewThread();
		//继承Thread
		newThread.new MyThread().start();
		
		//实现Runnable接口
		new Thread(newThread.new MyRunnable()).start();
		
		//实现Callable接口
		FutureTask<String> futureTask = new FutureTask<String>(newThread.new MyCallable());
		new Thread(futureTask).start();
		System.out.println("获取callable返回结果（同步阻塞）："+futureTask.get());
		
		System.out.println("main执行的线程："+Thread.currentThread());
	}
	
	
	class MyThread extends Thread{

		@Override
		public void run() {
			System.out.println("MyThread执行的线程："+Thread.currentThread());
		}
		
	}
	
	class MyRunnable implements Runnable{

		public void run() {
			System.out.println("MyRunnable执行的线程："+Thread.currentThread());
		}
		
	}
	
	class MyCallable implements Callable<String>{

		public String call() throws Exception {
			System.out.println("MyCallable执行的线程："+Thread.currentThread());
			Thread.sleep(5000);
			return "MyCallableResult";
		}
		
	}
}
