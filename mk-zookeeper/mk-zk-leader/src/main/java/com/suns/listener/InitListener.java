/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.listener <br>
 *
 * @author mk <br>
 * Date:2018-11-8 9:02 <br>
 */

package com.suns.listener;

import com.suns.utils.ZkClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ClassName: InitListener <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-8 9:02 <br>
 * @version
 */
public class InitListener implements ServletContextListener {

    @Value("${server.port}")
    private int port;
    @Value("${zookeeper.ip.port}")
    private String zookeeperConn;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext())
                .getAutowireCapableBeanFactory().autowireBean(this);
        ZkClientUtil.init(zookeeperConn,port);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ZkClientUtil.release();
    }
}
