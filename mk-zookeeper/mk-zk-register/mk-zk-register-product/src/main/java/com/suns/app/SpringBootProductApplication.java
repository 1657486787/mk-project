/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.app <br>
 *
 * @author mk <br>
 * Date:2018-11-5 16:01 <br>
 */

package com.suns.app;

import com.suns.listener.InitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * ClassName: SpringBootProductApplication <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-5 16:01 <br>
 * @version
 */
@SpringBootApplication(scanBasePackages = "com.suns")
public class SpringBootProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProductApplication.class);
    }

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new InitListener());
        return servletListenerRegistrationBean;
    }
}
