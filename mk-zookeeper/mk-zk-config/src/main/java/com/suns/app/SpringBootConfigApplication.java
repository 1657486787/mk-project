/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.app <br>
 *
 * @author mk <br>
 * Date:2018-11-8 10:08 <br>
 */

package com.suns.app;

import com.suns.listener.InitListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * ClassName: SpringBootConfigApplication <br>
 * Description: zk配置中心,监听/mk-config/db/url,如果里面的值有变化，那就更新数据源<br>
 *     访问地址：http://localhost:9401/config/sayHello
 * @author mk
 * @Date 2018-11-8 10:08 <br>
 * @version
 */
@SpringBootApplication(scanBasePackages = "com.suns")
@MapperScan("com.suns.dao")
public class SpringBootConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfigApplication.class,args);
    }

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new InitListener());
        return servletListenerRegistrationBean;
    }
}
