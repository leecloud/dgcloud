package com.lee.cloudadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
@MapperScan(basePackages = "com.lee.cloudadmin.dao")
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.lee"})
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class CloudAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAdminApplication.class, args);
    }
}
