package cn.edu.njtech.configserver7878;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServer7878Application {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServer7878Application.class, args);
    }

}
