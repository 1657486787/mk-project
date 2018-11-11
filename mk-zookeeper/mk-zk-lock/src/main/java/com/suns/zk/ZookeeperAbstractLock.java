package com.suns.zk;

import org.I0Itec.zkclient.ZkClient;

public abstract class ZookeeperAbstractLock extends AbstractLock {

    ZkClient zkClient = new ZkClient("127.0.0.1",5000);
    public static final String lock = "/mk-lock";
    protected static final String lock2 = "/lock2";
}
