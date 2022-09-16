package cn.edu.njtech.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@Slf4j
public class TestController {
    @Value("${name}")
    private String name;

    @GetMapping("/test/test")
    public String test() {
        log.info("当前加载配置文件信息为:[{}]", name);
        return name;
    }
}