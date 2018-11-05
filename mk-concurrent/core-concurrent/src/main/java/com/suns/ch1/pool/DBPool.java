/**
 * Project Name:core-concurrent <br>
 * File Name:DBPool.java <br>
 * Package Name:com.suns.ch1.pool <br>
 * @author Administrator
 * Date:2018年11月4日上午11:18:30 <br>
 * Copyright (c) 2018, mk有限公司 All Rights Reserved.
 */

package com.suns.ch1.pool;
/**
 * ClassName: DBPool <br>
 * Description: 数据库连接池，主要两个方法 获取连接 和释放连接
 * @author Administrator
 * @Date 2018年11月4日上午11:18:30 <br>
 * @version
 * @since JDK 1.6
 */

import java.sql.Connection;
import java.util.LinkedList;

public class DBPool {
	
	private LinkedList<Connection> pool = new LinkedList<Connection>();
	
	public DBPool(int initPoolSize) {
		if(initPoolSize>0) {
			for(int i=0;i<initPoolSize;i++) {
				pool.addLast(SqlConnectionImpl.fetConnection());
			}
		}
	}
	
	public Connection getConn(long mills) throws InterruptedException {
		synchronized (pool) {
			if(mills < 0) {
				while(pool.isEmpty()) {
					pool.wait();
				}
				return pool.removeFirst();
			}else {
				long overTime = System.currentTimeMillis() + mills;//计算超时时间
				long remainTime = mills;//等待时间
				while(pool.isEmpty() && remainTime > 0) {
					pool.wait(remainTime);
					remainTime = overTime - System.currentTimeMillis();
				}
				Connection connection  = null;
				if(!pool.isEmpty()) {
					connection = pool.removeFirst();
				}
				return connection;
			}
			
		}
	}

	public void releaseConn(Connection conn) {
		if(null != conn) {
			synchronized (pool) {
				pool.addLast(conn);
				pool.notifyAll();
			}
		}
	}
	
}

	