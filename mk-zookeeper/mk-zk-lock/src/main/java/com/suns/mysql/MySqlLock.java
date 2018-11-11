package com.suns.mysql;

import com.suns.mysql.mapper.ILockDao;
import com.suns.mysql.module.LockDo;
import com.suns.zk.AbstractLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("mysqlLock")
public class MySqlLock extends AbstractLock {

    private static final String lockName = "lock_use_mysql";

    @Autowired
    private ILockDao lockDao;

    @Override
    public boolean tryLock() {
        try {
            //往数据库里面插入数据，插入的字段为唯一键，如果能插入成功，代表获取锁成功
            LockDo lockDo = new LockDo();
            lockDo.setId(lockName);
            lockDao.addLock(lockDo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void waitLock() {
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unlock() {
        //删除数据
        lockDao.deleteById(lockName);
    }
}
