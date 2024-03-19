package org.example.Controller;

import org.example.Model.User;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/*
user controller class which handles rest requests (http) made though the postman api. performs
basic operations like CRUd here though paths define as URLs and various parameters being passed in the
body of the request data sent into the function. This calls the functions from the service object
by using dependency injection on the service object. the outputs are defined by the service functions
while the inputs are passed as JSON objects through the API, on which actions are done. this also h
handles the log in function and returns a JSON format result containing the token through the service
onject and displays as output on the POSTMAN API
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userServ;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public User save(@RequestBody User user){
        return userServ.saveUser(user);
    }

    @PostMapping("/signupall")
    public List<User> saveAll(@RequestBody List<User> allUsers){
        return userServ.saveAllUsers(allUsers);
    }

    @GetMapping("/findbyid")
    public void findById(@RequestParam(name = "id") String id){
        if(userServ.existUserById(id)){
            System.out.println(userServ.findUserById(id));
        }else{
            System.out.println("User is not present");
        }
    }
    @GetMapping("/getall")
    public List<User> findall(){
        return userServ.findAllUsers();
    }

    @DeleteMapping("/deleteUserById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") String id){
        try {
            boolean userExists = userServ.existUserById(id);
            if (userExists){
                userServ.deleteUserById(id);
                System.out.println("User id "+id+" successfully deleted");
            }else{
                System.out.println("Id does not exist");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @PostMapping("/deleteAllUsers")
    public void deleteAll(){
        try {
            userServ.deleteAllProducts();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @PostMapping("/updateuser")
    public User update(@RequestBody User user){
        User newUser = user;
        System.out.println("Id of new user is " + newUser.getId());
        if(userServ.existUserById(newUser.getId().toString())){
            User updatedUser = userServ.updateUser(user);
            return updatedUser;
        }
        System.out.println("User does not exists");
        return null;
    }

    @PostMapping("/findUserByEmail/by-email")
    public Boolean emailExists(@RequestBody String email){
        return userServ.existByEmail(email);
    }

    @PostMapping("/exists/login")
    public String loginExists(@RequestBody User user){
        String userEmail = user.getUserName();
        String password = user.getPassword1();

        return userServ.userLogin(userEmail, password);
    }
}
