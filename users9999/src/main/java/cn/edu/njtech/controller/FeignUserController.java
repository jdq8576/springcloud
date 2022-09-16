package cn.edu.njtech.controller;

import cn.edu.njtech.client.ProductClient;
import cn.edu.njtech.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tim
 * @date 2022/9/14 1:22 下午
 */
@RestController
@Slf4j
public class FeignUserController {

    //注入客户端对象
    @Autowired
    private ProductClient productClient;

    @GetMapping("/user/findAllFeignClient")
    public Map<String, Object> findAllFeignClient() {
        log.info("通过使用OpenFeign组件调用商品服务...");
        Map<String, Object> msg = productClient.findAll();
        return msg;
    }

    @GetMapping("/feign/test1")
    public Map<String, Object> test1(String id) {
        log.info("用来测试Openfiegn的GET方式参数传递");
        Map<String, Object> msg = productClient.findOne(id);
        log.info("调用返回信息:[{}]", msg);
        return msg;
    }

    @GetMapping("/user/save")
    public Map<String, Object> save(String productName) {
        log.info("接收到的商品信息名称:[{}]", productName);
        Map<String, Object> save = productClient.save(productName);
        log.info("调用成功返回结果: " + save);
        return save;
    }

    @GetMapping("/user/saveProduct")
    public Map<String, Object> saveProduct(Product product) {
        log.info("接收到的商品信息:[{}]", product);
        Map<String, Object> map = productClient.saveProduct(product);
        log.info("调用成功返回结果: " + map);
        return map;
    }

    @GetMapping("/user/testfeignhystrix")
    public Map<String, Object> testFeignHystrix() {
        Map<String, Object> map = new HashMap<>();
        return productClient.testHystrix("Tim");
    }
}
