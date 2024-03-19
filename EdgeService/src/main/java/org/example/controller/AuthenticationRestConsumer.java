package org.example.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("authentication-service/authentication")
public interface AuthenticationRestConsumer {

    @GetMapping("/generateToken/{id}")
    public String generateToken(@PathVariable("id") String id);
}
