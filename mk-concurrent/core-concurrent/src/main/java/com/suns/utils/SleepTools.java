/**
 * Project Name:core-concurrent <br>
 * File Name:SleepTools.java <br>
 * Package Name:com.suns.utils <br>
 * @author Administrator
 * Date:2018年11月3日下午9:55:57 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.utils;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: SleepTools <br>
 * Description: TODO
 * @author Administrator
 * @Date 2018年11月3日下午9:55:57 <br>
 * @version
 * @since JDK 1.6
 */
public class SleepTools {

	public static void second(int second) {
		try {
			TimeUnit.SECONDS.sleep(second);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void ms(int millisecond) {
		try {
			TimeUnit.MILLISECONDS.sleep(millisecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

	