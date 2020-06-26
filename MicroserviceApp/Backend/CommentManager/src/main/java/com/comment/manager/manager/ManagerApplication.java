package com.comment.manager.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableEurekaClient
@SpringBootApplication
@EnableAspectJAutoProxy
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
        System.out.println("COMMENT APP STARTED______________________________");
    }

}
