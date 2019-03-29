package com.lee.cloudbase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.lee.cloudbase.dao")
//@ComponentScan("com.*")
public class CloudBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudBaseApplication.class, args);
    }
}
