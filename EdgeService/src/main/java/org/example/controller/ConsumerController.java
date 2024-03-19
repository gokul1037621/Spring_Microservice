package org.example.controller;

import org.example.model.Category;
import org.example.model.Product;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("consumer")
public class ConsumerController {

    UserRestConsumer userRestConsumer;

    AuthenticationRestConsumer authenticationRestConsumer;

    ProductRestConsumer productRestConsumer;
    CategoryRestConsumer categoryRestConsumer;

    @Autowired
    public ConsumerController(UserRestConsumer userRestConsumer,
                              AuthenticationRestConsumer authenticationRestConsumer,
                              ProductRestConsumer productRestConsumer,
                              CategoryRestConsumer categoryRestConsumer) {
        this.userRestConsumer = userRestConsumer;

        this.authenticationRestConsumer = authenticationRestConsumer;
        this.productRestConsumer = productRestConsumer;
        this.categoryRestConsumer = categoryRestConsumer;
    }

    @GetMapping("/user/getall")
    public List<User> findallUsers(){
//        System.out.println(userRestConsumer.getClass().getSimpleName());
//        System.out.println("accessing from user service");
        return userRestConsumer.findall();
    }

    @GetMapping("/user/login")
    public String loginUser(@RequestBody User user){
//        System.out.println(userRestConsumer.getClass().getSimpleName());
//        System.out.println("accessing from user service");
        String token  = userRestConsumer.loginExists(user);
        return authenticationRestConsumer.generateToken(token);
    }



    @GetMapping("/authentication/authenticate/{id}")
    public String generateToken(@PathVariable("id") String token){
        System.out.println(authenticationRestConsumer.generateToken(token));
        return authenticationRestConsumer.generateToken(token);
    }

    @GetMapping("/product/getall")
    public List<Product> findallProducts(){
//        System.out.println(userRestConsumer.getClass().getSimpleName());
//        System.out.println("accessing from user service");
        return productRestConsumer.findall();
    }

    @GetMapping("/category/getall")
    public List<Category> findallCategories(){
//        System.out.println(userRestConsumer.getClass().getSimpleName());
//        System.out.println("accessing from user service");
        return categoryRestConsumer.findall();
    }

}
