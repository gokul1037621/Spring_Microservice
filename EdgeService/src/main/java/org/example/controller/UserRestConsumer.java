package org.example.controller;

import org.example.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@FeignClient("user-service/user")
public interface UserRestConsumer {

    @PostMapping("signup")
    public User save(@RequestBody User user);

    @GetMapping("/getall")
    public List<User> findall();

    @PostMapping("/exists/login")
    public String loginExists(@RequestBody User user);
}
