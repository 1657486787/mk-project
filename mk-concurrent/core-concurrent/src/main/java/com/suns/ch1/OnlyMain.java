package com.suns.ch1;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 打印虚拟机中所有线程信息
 * @author Administrator
 *
 */
public class OnlyMain {

	public static void main(String[] args) {
		//虚拟机线程管理的接口
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds());
		for(ThreadInfo info : threadInfos) {
			System.out.println(info);
		}
		
		
		ThreadInfo[] dumpAllThreads = threadMXBean.dumpAllThreads(false, false);
		for(ThreadInfo info : dumpAllThreads ) {
			System.out.println(info);
		}
		
	}
}
