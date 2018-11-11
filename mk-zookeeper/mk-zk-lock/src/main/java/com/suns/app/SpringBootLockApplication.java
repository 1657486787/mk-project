package com.suns.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.suns")
@MapperScan(basePackages = "com.suns.mysql.mapper")
public class SpringBootLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLockApplication.class,args);
    }

}
