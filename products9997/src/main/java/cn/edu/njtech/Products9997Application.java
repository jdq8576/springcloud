package cn.edu.njtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker  //用来开启断路器
public class Products9997Application {

    public static void main(String[] args) {
        SpringApplication.run(Products9997Application.class, args);
    }

}
