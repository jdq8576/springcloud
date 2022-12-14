package cn.edu.njtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClient7879Application {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClient7879Application.class, args);
    }

}
