package org.example.controller;

import org.example.model.Product;
import org.example.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@FeignClient("productncategory-service/product")
public interface ProductRestConsumer {

    @GetMapping("/getall")
    public List<Product> findall();
}
