/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.app <br>
 *
 * @author mk <br>
 * Date:2018-11-8 8:56 <br>
 */

package com.suns.app;

import com.suns.listener.InitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * ClassName: SpringBootLeaderApplication <br>
 * Description: 集群选举：修改application.properties的server.port，启动多次 <br>
 *     访问地址http://localhost:9301/leader/getServerInfo
 *     http://localhost:9302/leader/getServerInfo
 *     http://localhost:9303/leader/getServerInfo
 *
 * @author mk
 * @Date 2018-11-8 8:56 <br>
 * @version
 */
@SpringBootApplication(scanBasePackages = "com.suns")
public class SpringBootLeaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLeaderApplication.class,args);
    }

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(new InitListener());
        return servletListenerRegistrationBean;
    }
}
