package com.suns.syn;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成唯一订单号
 * 并发情况下为有线程安全问题
 * 解决办法：
 * 1.利用jdk自带的锁，如在方法上加synchronized或者使用Lock
 * 2.利用分布式环境的锁，如mysql，redis,zookeeper
 */
public class OrderNumGenerator {

    public static int count = 0;
    /*public synchronized static String generator(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String format = simpleDateFormat.format(new Date());
        return format+">>>>>>>>"+(++count);
    }*/

    public static String generator(){
        synchronized (OrderNumGenerator.class){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String format = simpleDateFormat.format(new Date());
            return format+">>>>>>>>"+(++count);
        }
    }
}
