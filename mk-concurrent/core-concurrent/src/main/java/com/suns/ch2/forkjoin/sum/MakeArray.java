/**
 * Project Name:core-concurrent <br>
 * File Name:MakeArray.java <br>
 * Package Name:com.suns.ch2.forkjoin.sum <br>
 * @author Administrator
 * Date:2018年11月4日下午4:49:53 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.ch2.forkjoin.sum;

import java.util.Random;

/**
 * ClassName: MakeArray <br>
 * Description: TODO
 * @author Administrator
 * @Date 2018年11月4日下午4:49:53 <br>
 * @version
 * @since JDK 1.6
 */

public class MakeArray {
	
	public static int arrayLength = 400;
	public static int[] markArray() {
		Random random = new Random();
		int []array = new int[arrayLength];
		for(int i=0;i<arrayLength;i++) {
			array[i] = random.nextInt(arrayLength * 3);
		}
		return array;
	}

}

	