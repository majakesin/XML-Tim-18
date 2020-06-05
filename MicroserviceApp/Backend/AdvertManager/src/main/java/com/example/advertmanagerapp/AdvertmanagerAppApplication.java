package com.example.advertmanagerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
public class AdvertmanagerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvertmanagerAppApplication.class, args);
        System.out.println("ADVERT MANAGER STARTED______________________________");
    }

}
