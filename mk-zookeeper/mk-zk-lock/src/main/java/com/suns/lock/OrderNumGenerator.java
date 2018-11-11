package com.suns.lock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生成唯一订单号
 * 并发情况下为有线程安全问题
 * 解决办法：
 * 1.利用jdk自带的锁，如在方法上加synchronized或者使用Lock
 * 2.利用分布式环境的锁，如mysql，redis,zookeeper
 */
public class OrderNumGenerator {

    public static int count = 0;
    private static Lock lock = new ReentrantLock();
    public static String generator(){
        try {
            lock.lock();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String format = simpleDateFormat.format(new Date());
            return format+">>>>>>>>"+(++count);
        } finally {
            lock.unlock();
        }
    }
}
