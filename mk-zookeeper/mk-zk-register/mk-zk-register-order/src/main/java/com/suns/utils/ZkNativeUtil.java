/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.utils <br>
 *
 * @author mk <br>
 * Date:2018-11-6 10:52 <br>
 */

package com.suns.utils;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * ClassName: ZkNativeUtil <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-6 10:52 <br>
 * @version
 */
public class ZkNativeUtil {

    private static final String BASE_SERVICES = "/mk-services";
    private static final String SERVICE_NAME="/mk-products";
    private static final String child ="/child";
    private static ZooKeeper zooKeeper = null;


    public static void init(String zookeeperConn) {
        try {
            zooKeeper = new ZooKeeper(zookeeperConn, 50000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if(Event.EventType.NodeCreated.equals(watchedEvent.getType())){
                        System.out.println(">>>>>>>>>>>>>NodeCreated:"+watchedEvent.getPath());
                    }
                    if(Event.EventType.NodeDeleted.equals(watchedEvent.getType())){
                        System.out.println(">>>>>>>>>>>>>NodeDeleted:"+watchedEvent.getPath());
                    }
                    if(Event.EventType.NodeChildrenChanged.equals(watchedEvent.getType())){
                        System.out.println(">>>>>>>>>>>>>NodeChildrenChanged:"+watchedEvent.getPath());
                        updateServiceList();
                    }

                }
            });
            updateServiceList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> updateServiceList()  {
      return updateServiceList(BASE_SERVICES + SERVICE_NAME);
    }

    public static List<String> updateServiceList(String path) {
        List<String> childrenList = null;
        try {
            childrenList = zooKeeper.getChildren(path , true);
            if(null == childrenList){
                return null;
            }
            for(String node : childrenList){
                byte[] data = zooKeeper.getData(path + "/"+ node, true, null);
                String nodeValue = new String(data,"UTF-8");
                System.out.println(path + "/"+ node+"------>"+nodeValue);

                if(LoadBalance.service_list.contains(nodeValue)){
                    System.out.println(nodeValue +" exists in ["+LoadBalance.service_list+"]. no need add.");
                }else{
                    LoadBalance.service_list.add(nodeValue);
                }
            }

            System.out.println("获取所有订单服务,"+childrenList);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return childrenList;
    }

}
