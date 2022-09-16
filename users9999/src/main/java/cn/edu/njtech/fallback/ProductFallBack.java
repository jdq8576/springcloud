package cn.edu.njtech.fallback;

import cn.edu.njtech.client.ProductClient;
import cn.edu.njtech.entity.Product;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductFallBack implements ProductClient {

    @Override
    public Map<String, Object> testHystrix(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "我是客户端的Hystrix服务实现!!!");
        return map;
    }

    @Override
    public Map<String, Object> findAll() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "我是客户端的Hystrix服务实现!!!");
        return map;
    }

    @Override
    public Map<String, Object> findOne(String productId) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "我是客户端的Hystrix服务实现!!!");
        return map;
    }

    @Override
    public Map<String, Object> save(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "我是客户端的Hystrix服务实现!!!");
        return map;
    }

    @Override
    public Map<String, Object> saveProduct(Product product) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "我是客户端的Hystrix服务实现!!!");
        return map;
    }
}