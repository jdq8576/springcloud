package cn.edu.njtech.controller;

import cn.edu.njtech.entity.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tim
 * @date 2022/9/14 11:11 上午
 */
@RestController
@Slf4j
public class ProductController {
    @Value("${server.port}")
    private int port;

    @GetMapping("/product/findAll")
    public Map<String, Object> findAll() {
        log.info("商品服务查询所有调用成功,当前服务端口:[{}]", port);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "服务调用成功,服务提供端口为: " + port);
        map.put("status", true);
        return map;
    }

    @GetMapping("/product/find")
    public Map<String, Object> find(String id) {
        log.info("商品服务查询商品信息调用成功,当前服务端口:[{}]", port);
        log.info("当前接收商品信息的id:[{}]", id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "商品服务查询单个商品信息调用成功,当前服务端口: " + port);
        map.put("status", true);
        map.put("id", id);
        return map;
    }


    @GetMapping("/product/findOne")
    public Map<String, Object> findOne(String productId) {
        log.info("商品服务查询单个商品信息调用成功,当前服务端口:[{}]", port);
        log.info("当前接收商品信息的id:[{}]", productId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "商品服务查询单个商品信息调用成功,当前服务端口: " + port);
        map.put("status", true);
        map.put("productId", productId);
        return map;
    }

    @PostMapping("/product/savename")
    public Map<String, Object> save(String name) {
        log.info("商品服务保存商品调用成功,当前服务端口:[{}]", port);
        log.info("当前接收商品名称:[{}]", name);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "商品服务保存商品调用成功,当前服务端口: " + port);
        map.put("status", true);
        map.put("name", name);
        return map;
    }

    @PostMapping("/product/saveProduct")
    public Map<String, Object> saveProduct(@RequestBody Product product) {
        log.info("商品服务保存商品信息调用成功,当前服务端口:[{}]", port);
        log.info("当前接收商品名称:[{}]", product);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "商品服务保存商品调用成功,当前服务端口: " + port);
        map.put("status", true);
        map.put("product", product);
        return map;
    }

    //服务熔断
    @GetMapping("/product/break")
    @HystrixCommand(fallbackMethod = "testBreakFall")
    public String testBreak(int id) {
        log.info("接收的商品id为: " + id);
        if (id <= 0) {
            throw new RuntimeException("数据不合法!!!");
        }
        return "当前接收商品id: " + id;
    }

    public String testBreakFall(int id) {
        return "当前数据不合法: " + id;
    }

    //默认熔断方法
    public Map<String, Object> defaultFallBack() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "当前服务不可用,触发熔断!");
        return map;
    }

    @PostMapping("/product/update")  //requestBody: 将json格式字符串转为对应对象信息
    @HystrixCommand(defaultFallback = "defaultFallBack")
    public Map<String, Object> update(@RequestBody Product product) {
        Map<String, Object> map = new HashMap<>();
        log.info("商品服务,接收到商品信息: [{}]", product);
        map.put("status", true);
        map.put("msg", "根据商品id查询商品信息成功!当前服务端口:" + port);
        map.put("product", product);
        return map;
    }

    @PostMapping("/product/save")
    @HystrixCommand(defaultFallback = "defaultFallBack")
    public Map<String, Object> save(@RequestParam("name") String name, int id) {
        Map<String, Object> map = new HashMap<>();
        log.info("商品服务,接收到商品name为: [{}]", name);
        log.info("商品服务,接收到商品id为: [{}]", id);
        map.put("status", true);
        map.put("msg", "根据商品id查询商品信息成功!当前服务端口:" + port);
        map.put("name", name);
        return map;
    }

    @GetMapping("/product/hystrix")
    @HystrixCommand(defaultFallback = "defaultFallBack")
    public Map<String, Object> testHystrix(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "服务端");
        return map;
    }

}
