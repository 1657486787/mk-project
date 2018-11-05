/**
 * Project Name:core-concurrent <br>
 * File Name:SumForkJoin.java <br>
 * Package Name:com.suns.ch2.forkjoin.sum <br>
 * @author Administrator
 * Date:2018年11月4日下午4:59:56 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.ch2.forkjoin.sum;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import com.suns.utils.SleepTools;

/**
 * ClassName: SumForkJoin <br>
 * Description: 使用ForkJoin来统计数据的和
 * @author Administrator
 * @Date 2018年11月4日下午4:59:56 <br>
 * @version
 * @since JDK 1.6
 */
public class SumForkJoin {

	private static int subArraySizePer = MakeArray.arrayLength/10;
	
	private static class MyRecursiveTask extends RecursiveTask<Integer>{

		private int [] array;
		private int fromIndex;
		private int toIndex;
		
		public MyRecursiveTask(int[] array, int fromIndex, int toIndex) {
			this.array = array;
			this.fromIndex = fromIndex;
			this.toIndex = toIndex;
		}

		@Override
		protected Integer compute() {
			if(toIndex - fromIndex < subArraySizePer) {
				int count = 0;
				for(int i=fromIndex;i<toIndex;i++) {
					SleepTools.ms(1);//休眠1ms
					count += array[i];
				}
				return count;
			}else {
				//fromIndex .......middleIndex.........toIndex
				int middleIndex = (toIndex+fromIndex)/2;
				MyRecursiveTask leftArray = new MyRecursiveTask(array, fromIndex,middleIndex);
				MyRecursiveTask rightArray = new MyRecursiveTask(array, middleIndex+1,toIndex);
				invokeAll(leftArray, rightArray);
				Integer result = leftArray.join()+rightArray.join();
				return result;
			}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int[] array = MakeArray.markArray();
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		MyRecursiveTask myRecursiveTask = new MyRecursiveTask(array,0,array.length-1);
		long start = System.currentTimeMillis();
		forkJoinPool.invoke(myRecursiveTask);//同步调用
		Integer result = myRecursiveTask.join();
		System.out.println("count:"+result +" time:"+(System.currentTimeMillis()-start));
		
	}
	
	
}

	