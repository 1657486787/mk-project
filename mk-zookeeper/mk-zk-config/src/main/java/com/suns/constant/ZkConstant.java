/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.constant <br>
 *
 * @author mk <br>
 * Date:2018-11-7 9:27 <br>
 */

package com.suns.constant;

/**
 * ClassName: ZkConstant <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-7 9:27 <br>
 * @version
 */
public class ZkConstant {

    public static int CONNECTIONTIMEOUT_DEFAULT = 50000;
    /**
     * 服务注册与发现
     */
    public static final String BASE_SERVICES = "/mk-services";
    public static final String SERVICE_NAME="/mk-products";
    public static final String CHILD ="/child";

    public static final String leader = "/mk-leader";//集群选举

    public static final String config = "/mk-config/db/url";//配置中心
    public static final String config_db1 = "jdbc:mysql://10.50.10.201:3306/hhn_user1";
    public static final String config_db2 = "jdbc:mysql://10.50.10.201:3306/hhn_user2";
}
