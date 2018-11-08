/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.zk <br>
 *
 * @author mk <br>
 * Date:2018-11-7 9:16 <br>
 */

package com.suns.zk;

import com.suns.constant.ZkConstant;
import org.I0Itec.zkclient.ZkClient;

/**
 * ClassName: ZkClientServiceRegister <br>
 * Description: zkClient实现服务注册 <br>
 * @author mk
 * @Date 2018-11-7 9:16 <br>
 * @version
 */
public class ZkClientServiceRegister {

    public static void register(String zookeeperConn, String hostAddress, int port) {
        String parentPath = ZkConstant.BASE_SERVICES + ZkConstant.SERVICE_NAME;
        ZkClient zkClient = new ZkClient(zookeeperConn, ZkConstant.CONNECTIONTIMEOUT_DEFAULT);
        zkClient.createPersistent(parentPath,true);
        String server_path = hostAddress+":"+port;//拼接ip:port
        String ephemeralSequential = zkClient.createEphemeralSequential(parentPath + ZkConstant.CHILD, server_path);
        System.out.println("服务注册["+server_path+"]成功...");
    }
}
