package com.suns.zk;

import com.suns.simple.OrderNumGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class OrderService implements Runnable{


//    private MyLock zkLock = new ZookeeperDistributeLock();
    private MyLock zkLock = new ZookeeperDistributeLock2();

    @Override
    public void run() {
        getNumber();
    }

    public void getNumber() {
        try {
            zkLock.lock();
            String orderNum = OrderNumGenerator.generator();
            System.out.println(Thread.currentThread().getName()+" 生成订单orderNum>>>>>"+orderNum);
//                Thread.sleep(2000);//模拟业务操作
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("生成唯一订单号");
        for(int i=0;i<50;i++){
            new Thread(new OrderService(),"thread-"+i).start();
        }
    }
}
