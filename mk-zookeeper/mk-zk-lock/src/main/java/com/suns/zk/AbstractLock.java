package com.suns.zk;

/**
 * 模版方法模式
 * 如这个模版下面可以有mysql，redis,zk等具体的实现
 */
public abstract class AbstractLock implements MyLock {

    public void lock(){
        if(tryLock()){
            System.out.println("获取到锁资源"+Thread.currentThread().getName());
        }else{
//            System.out.println("抛异常表示插入失败，休眠几分钟 ,/再次尝试获取锁"+Thread.currentThread().getName());
            waitLock();//抛异常表示插入失败，休眠几分钟
            lock();//再次尝试获取锁
        }
    }

    public abstract boolean tryLock();

    public abstract void waitLock();
}
