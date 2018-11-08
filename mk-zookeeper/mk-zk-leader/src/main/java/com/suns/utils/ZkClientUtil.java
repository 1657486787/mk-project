/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.utils <br>
 *
 * @author mk <br>
 * Date:2018-11-8 9:05 <br>
 */

package com.suns.utils;

import com.suns.constant.ZkConstant;
import com.suns.listener.ElectionMaster;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * ClassName: ZkClientUtil <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-8 9:05 <br>
 * @version
 */
public class ZkClientUtil {

    private static ZkClient zkClient;

    public static void init(String zookeeperConn, int port) {
        zkClient = new ZkClient(zookeeperConn, ZkConstant.CONNECTIONTIMEOUT_DEFAULT);
        System.out.println("zkClient init ."+zookeeperConn);
        createElectionMaster(port);

        //集群选举不用监听subscribeChildChanges ，而是监听subscribeDataChanges 的handleDataDeleted 事件
       /* zkClient.subscribeChildChanges(ZkConstant.leader,(String path, List<String> list)->{
            System.out.println("subscribeChildChanges...."+path+","+list);
        });*/
        zkClient.subscribeDataChanges(ZkConstant.leader, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("subscribeDataChanges.handleDataChange...."+s+","+o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("subscribeDataChanges.handleDataDeleted...."+s);
                System.out.println("主节点已经挂掉了，重新开始选举master");
                createElectionMaster(port);
            }
        });


    }

    public static void createElectionMaster(int port) {
        try{
            zkClient.createEphemeral(ZkConstant.leader);
            ElectionMaster.isSurvival = true;
            System.out.println("创建master节点成功，恭喜["+port+"]选举master成功！");
        }catch (Exception e){
            ElectionMaster.isSurvival = false;
            System.out.println("创建master节点失败，["+port+"]选举master失败！");
        }
    }

    public static void release(){
        if(null != zkClient){
            zkClient.close();
        }
    }
}
