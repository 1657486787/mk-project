package com.suns;

import com.suns.simple.OrderNumGenerator;
import com.suns.zk.MyLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {com.suns.app.SpringBootLockApplication.class})
public class MysqlTest {

    @Resource
    private MyLock mysqlLock;

    @Test
    public void testAdd() throws InterruptedException {
        System.out.println("生成唯一订单号");
        for(int i=0;i<50;i++){
            new Thread(new MyRunnable(),"thread-"+i).start();
        }
        System.out.println("Thread.currentThread():"+Thread.currentThread().getName());
        Thread.currentThread().join();
    }

    public class MyRunnable implements Runnable{
        @Override
        public void run() {
            try {
                mysqlLock.lock();
                String orderNum = OrderNumGenerator.generator();
                System.out.println("orderNum>>>>>"+orderNum);
                Thread.sleep(2000);//模拟业务操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mysqlLock.unlock();
            }
        }
    }
}
