/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.app <br>
 *
 * @author mk <br>
 * Date:2018-11-6 10:21 <br>
 */

package com.suns.app;

import com.suns.listener.InitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: SpringBootOrderApplication <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-6 10:21 <br>
 * @version
 */
@SpringBootApplication(scanBasePackages = "com.suns")
public class SpringBootOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOrderApplication.class);
    }


    @Bean
    public ServletListenerRegistrationBean tt(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new InitListener());
        return servletListenerRegistrationBean;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
