/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.utils <br>
 *
 * @author mk <br>
 * Date:2018-11-8 14:22 <br>
 */

package com.suns.utils;

import com.suns.constant.ZkConstant;
import com.suns.db.SunsDataSource;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * ClassName: ZkClientUtil <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-8 14:22 <br>
 * @version
 */
public class ZkClientUtil {


    private static ZkClient zkClient;
    public static void init(String zookeeperConn){
        //这里一定是增加new MyZkSerializer()，不然节点的值有可能有问题
        zkClient = new ZkClient(zookeeperConn, ZkConstant.CONNECTIONTIMEOUT_DEFAULT,500,new MyZkSerializer());

        createUrlNode();

        zkClient.subscribeDataChanges(ZkConstant.config, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("dataSource changed....."+s+","+o);
                SunsDataSource dataSource = (SunsDataSource) RunTimeContext.getBean("dataSource");
                dataSource.setUrl(o.toString());
                dataSource.changeDataSource();
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("handleDataDeleted....."+s);
            }
        });
        zkClient.writeData(ZkConstant.config,"");
    }

    private static void createUrlNode() {
        try{
            zkClient.readData(ZkConstant.config);
            System.out.println(ZkConstant.config+" 配置中心根节点已存在");
        } catch (Exception e){
            e.printStackTrace();
            zkClient.createPersistent(ZkConstant.config,true);
            System.out.println(ZkConstant.config+" 创建配置中心根节点");
        }

    }
}
