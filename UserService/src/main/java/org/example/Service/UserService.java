package org.example.Service;

import org.bson.types.ObjectId;
import org.example.Model.User;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    // user service class to provide function implementations to perform changes in db or
    // execute certain tasks which are invoked by the controller during run time

    @Autowired
    private UserRepository userRepo; // user repository object
    // token service object to invoke token creation methods specific to user id

    // dependency injection through a constructor for multiple objects

    // function to save a user entry into the table
    public User saveUser(User user){
        return userRepo.save(user);
    }

    // function to save a complete list of users to the table
    public List<User> saveAllUsers(List<User> allUsers){
        return userRepo.saveAll(allUsers);
    }

    // function to search and return a user based on the id, if one object is present. Returns the first occurence
    public User findUserById(String id){
        Optional<User> findUserObj = userRepo.findById(new ObjectId(id));
        return findUserObj.get();
    }


    // function to return the entire list of users present in table
    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    // function to check if a user with the given id exists in table or not
    public boolean existUserById(String id){
        System.out.println(userRepo.existsById(new ObjectId(id)));
        return userRepo.existsById(new ObjectId(id));
    }

    // function to delete a user record from table if it exists based on user id
    public void deleteUserById(String id){
        userRepo.deleteById(new ObjectId(id));
    }

    // function to delete all users from the table
    public void deleteAllProducts(){
        userRepo.deleteAll();
    }

    // function to update a user record based on the given user id
    // save and flush is used to commit changes to db immediately
    public User updateUser(User user){
        User userObj = userRepo.save(user);
        return userObj;
    }


    // function to check if user exists based on given email address
    // manually implemented here to return true on first occurence
    public Boolean existByEmail(String email) {
//        Optional<User> usersObj = Optional.ofNullable(userRepo.getUserByUsername(email));
//        System.out.println(usersObj);
//        if(!usersObj.isEmpty()){
//            return true;
//        }
//        return false;
        ArrayList<User> userObj = (ArrayList<User>) userRepo.findAll();
        for(User u : userObj){
            System.out.println(u);
            if(u.getUserName().equals(email)){
                return true;
            }

        }
        return false;
    }


    //function to log in based on given user name and password
    // token is created for a particular user id on successful authentication
    // both user name and password have to match or it fails.
    // manually implemented to succeed on first match
    public String userLogin(String email, String password ) {
        boolean foundUsers = existByEmail(email);
        if(foundUsers) {
            ArrayList<User> userObj = (ArrayList<User>) userRepo.findAll();
            for(User user : userObj){

                if(user.getPassword1().equals(password)) {
                    return user.getId().toString();
                }
            }

         }

        return null;
    }}
