/**
 * Project Name:core-concurrent <br>
 * File Name:SumNormal.java <br>
 * Package Name:com.suns.ch2.forkjoin.sum <br>
 * @author Administrator
 * Date:2018年11月4日下午4:53:41 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.ch2.forkjoin.sum;

import com.suns.utils.SleepTools;

/**
 * ClassName: SumNormal <br>
 * Description: 使用普通for循环来统计数据的和
 * @author Administrator
 * @Date 2018年11月4日下午4:53:41 <br>
 * @version
 * @since JDK 1.6
 */
public class SumNormal {

	public static void main(String[] args) {
		int[] array = MakeArray.markArray();
		int count = 0;
		
		long start = System.currentTimeMillis();
		for(int i=0;i<array.length;i++) {
//			SleepTools.ms(1);//休眠1ms
			count += array[i];
		}
		System.out.println("count:"+count +" time:"+(System.currentTimeMillis()-start));
	}
}

	