/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.utils <br>
 *
 * @author mk <br>
 * Date:2018-11-7 9:45 <br>
 */

package com.suns.utils;

import com.suns.constant.ZkConstant;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * ClassName: ZkClientUtil <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-7 9:45 <br>
 * @version
 */
public class ZkClientUtil {

    private static ZkClient zkClient = null;
    static String parentPath = ZkConstant.BASE_SERVICES + ZkConstant.SERVICE_NAME;

    public static void init(String zookeeperConn) {
        zkClient = new ZkClient(zookeeperConn, ZkConstant.CONNECTIONTIMEOUT_DEFAULT);
        zkClient.subscribeChildChanges(parentPath, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("handleChildChange.....s=" + s + ",list="+list);
                updateServiceList();
            }
        });
    }

    public static void updateServiceList() {
        List<String> childrenList = zkClient.getChildren(parentPath);
        if(CollectionUtils.isEmpty(childrenList)){
            System.out.println(parentPath+" is no childrenList...over");
            return ;
        }
        List<String> service_list = new ArrayList<>();//产品服务ip+port列表,由于有可能新增也有可能删除，所以这里需要每次都new
        for(String node : childrenList){
            String nodeValue = zkClient.readData(parentPath + "/" + node);
            service_list.add(nodeValue);
        }
        LoadBalance.service_list = service_list;
        System.out.println("获取所有订单服务,"+childrenList);
    }
}
