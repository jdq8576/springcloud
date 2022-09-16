package cn.edu.njtech.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class UserController {
    @GetMapping("/user/findAll")
    public String findAll() {
        log.info("调用用户服务...");
        //1.使用restTemplate调用商品服务
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://localhost:9998/product/findAll",
                String.class);
        return forObject;
    }

    /**
     * 使用discovery Client形式调用
     */

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/user/test1")
    public Map<Object, Object> testDiscoveryClient() {
        //获取服务列表
        List<ServiceInstance> products = discoveryClient.getInstances("products");
        log.info("{}", products.size());
        for (ServiceInstance product : products) {
            log.info("服务主机:[{}]", product.getHost());
            log.info("服务端口:[{}]", product.getPort());
            log.info("服务地址:[{}]", product.getUri());
            log.info("====================================");
        }
        Map<Object, Object> map = new HashMap<>();
        map.put("msg", "Hello World!");
        return map;
    }

    /**
     * loadBalance Client形式调用
     */

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/user/test2")
    public Map<Object, Object> testLoadBalancerClient() {
        ServiceInstance product = loadBalancerClient.choose("products");//地址  轮询策略
        log.info("服务主机:[{}]", product.getHost());
        log.info("服务端口:[{}]", product.getPort());
        log.info("服务地址:[{}]", product.getUri());
        final String host = product.getHost();
        final int port = product.getPort();
        String url = "http://" + product.getHost() + ":" + product.getPort() + "/product/findAll";
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url,
                String.class);
        Map<Object, Object> map = new HashMap<>();
        map.put("msg", forObject);
        return map;
    }

    /**
     * @loadBalanced
     */

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user/test3")
    public Map<Object, Object> testLoadBalancerRestTemplate() {
        String forObject = restTemplate.getForObject("http://products/product/findAll", String.class);
        Map<Object, Object> map = new HashMap<>();
        map.put("msg", forObject);
        return map;
    }


}