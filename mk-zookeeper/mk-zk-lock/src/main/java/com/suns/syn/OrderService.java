package com.suns.syn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class OrderService {

    private static List list = new ArrayList();
    private static CountDownLatch cdl = new CountDownLatch(50);

    public static class MyRunnable implements Runnable{
        @Override
        public void run() {
            cdl.countDown();
            String orderNum = OrderNumGenerator.generator();
//            System.out.println("orderNum>>>>>"+orderNum);
            list.add(orderNum);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("生成唯一订单号");
        for(int i=0;i<50;i++){
            new Thread(new MyRunnable(),"thread-"+i).start();
        }
        cdl.await();
        Thread.sleep(1000);
        Collections.sort(list);
        for(Object str : list){
            System.out.println(str);
        }
    }
}
