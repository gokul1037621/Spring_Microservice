package org.example.controller;

import org.example.model.Category;
import org.example.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@FeignClient("productncategory-service/category")
public interface CategoryRestConsumer {

    @GetMapping("/getall")
    public List<Category> findall();
}
