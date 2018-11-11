package com.suns.zk;

import org.I0Itec.zkclient.IZkDataListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component("zkLock")
public class ZookeeperDistributeLock extends ZookeeperAbstractLock{

    private CountDownLatch cdl = null;

    @Override
    public boolean tryLock() {
        try {
            zkClient.createEphemeral(lock);
            return true;
        } catch (RuntimeException e) {
//            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void waitLock() {

        IZkDataListener zkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
//                System.out.println("该节点["+s+"]已经修改"+Thread.currentThread().getName());
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
//                System.out.println("该节点["+s+"]已经被删除了，竞争锁开始，"+Thread.currentThread().getName());
                // 唤醒被等待的线程
                if(null!=cdl){
                    cdl.countDown();
                }
            }
        };
        // 注册事件
        zkClient.subscribeDataChanges(lock, zkDataListener);

        try {
            if(zkClient.exists(lock)){
                cdl = new CountDownLatch(1);
                cdl.await();//等待，一直等到接受到事件通知
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 删除监听
        zkClient.unsubscribeDataChanges(lock,zkDataListener);
    }

    @Override
    public void unlock() {
        if(null != zkClient){
            zkClient.delete(lock);
            zkClient.close();
            System.out.println("释放锁资源");
        }
    }
}
