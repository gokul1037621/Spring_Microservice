package org.example.controller;


import org.bson.types.ObjectId;
import org.example.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    TokenService tokenService;

    @GetMapping("/generateToken/{id}")
    public String generateToken(@PathVariable("id") String id){
        String token =  tokenService.createTokenFunction(new ObjectId(id));
        System.out.println(token);
        System.out.println(tokenService.getUserIdToken(token));
        return token;
    }
}
