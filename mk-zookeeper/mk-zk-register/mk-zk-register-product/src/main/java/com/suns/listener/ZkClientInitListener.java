/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.listener <br>
 *
 * @author mk <br>
 * Date:2018-11-7 9:14 <br>
 */

package com.suns.listener;

import com.suns.zk.ZkClientServiceRegister;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ClassName: ZkClientInitListener <br>
 * Description: zkClient实现服务注册  <br>
 * @author mk
 * @Date 2018-11-7 9:14 <br>
 * @version
 */
public class ZkClientInitListener implements ServletContextListener {

    @Value("${server.port}")
    private int port;
    @Value("${zookeeper.ip.port}")
    private String zookeeperConn;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String hostAddress = null;
        try {
            //一定要加这句，不然启动报错：Caused by: org.springframework.boot.web.server.WebServerException: Unable to start embedded Tomcat
            WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext())
                    .getAutowireCapableBeanFactory().autowireBean(this);

            hostAddress = InetAddress.getLocalHost().getHostAddress();
            ZkClientServiceRegister.register(zookeeperConn,hostAddress,port);
            System.out.println("ZkClientInitListener start ["+hostAddress+":"+port+"]...");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed...");
    }
}
