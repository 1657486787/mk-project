/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.zk <br>
 *
 * @author mk <br>
 * Date:2018-11-5 16:10 <br>
 */

package com.suns.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

/**
 * ClassName: ServiceRegister <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-5 16:10 <br>
 * @version
 */
@Component
public class ServiceRegister {

    private static final String BASE_SERVICES = "/mk-services";
    private static final String SERVICE_NAME="/mk-products";
    private static final String child ="/child";

    public static void register(String zookeeperConn, String host, int port){
        System.out.println("zookeeperConn:"+ zookeeperConn);
        try {
            ZooKeeper zooKeeper = new ZooKeeper(zookeeperConn, 5000, null);
            Stat exists = zooKeeper.exists(BASE_SERVICES + SERVICE_NAME, false);
            if(null == exists){
//                createRecursion(BASE_SERVICES + SERVICE_NAME,"".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
                createRecursion(zooKeeper,BASE_SERVICES + SERVICE_NAME,"");
            }

            String server_path = host+":"+port;//拼接ip:port
            zooKeeper.create(BASE_SERVICES+SERVICE_NAME+child,server_path.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);

            System.out.println("服务注册["+server_path+"]成功...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void createRecursion(ZooKeeper zookeeper,String path,String data) throws KeeperException, InterruptedException {
        if(null == path || "".equals(path)){
            System.out.println("节点["+path+"]为空");
            return;
        }
        String paths[] = path.substring(1,path.length()).split("/");
        for(int i=0;i<paths.length;i++){
            String childPath = "";
            for(int j=0;j<=i;j++){
                childPath += "/" + paths[j];
            }
            create(zookeeper,childPath,data);
        }

        Stat exists = zookeeper.exists(path, true);
        if(null != exists){
            System.out.println("节点["+path+"]已存在，不能新增");
            return;
        }
        String result = zookeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("create:"+"["+path+"-->"+data+"],result:"+result);
    }

    private static void create(ZooKeeper zookeeper,String path,String data) throws KeeperException, InterruptedException {
        Stat exists = zookeeper.exists(path, true);
        if(null != exists){
            System.out.println("节点["+path+"]已存在，不能新增");
            return;
        }
        String result = zookeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("create:"+"["+path+"-->"+data+"],result:"+result);
    }


}
