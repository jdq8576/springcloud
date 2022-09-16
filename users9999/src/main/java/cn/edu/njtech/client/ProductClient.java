package cn.edu.njtech.client;

import cn.edu.njtech.entity.Product;
import cn.edu.njtech.fallback.ProductFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

//value属性用来指定:调用服务名称
@FeignClient(value = "products", fallback = ProductFallBack.class)
public interface ProductClient {

    //书写服务调用路径
    @GetMapping("/product/findAll")
    Map<String, Object> findAll();

    @GetMapping("/product/findOne")
    Map<String, Object> findOne(@RequestParam("productId") String productId);

    @PostMapping("/product/savename")
    Map<String, Object> save(@RequestParam("name") String name);

    @PostMapping("/product/saveProduct")
    Map<String, Object> saveProduct(@RequestBody Product product);

    @GetMapping("/product/hystrix")
    Map<String, Object> testHystrix(@RequestParam("name") String name);
}