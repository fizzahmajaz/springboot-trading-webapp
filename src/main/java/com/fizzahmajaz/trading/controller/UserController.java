package com.fizzahmajaz.trading.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fizzahmajaz.trading.entity.User;
import com.fizzahmajaz.trading.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Register the new user (Create user api)
    @PostMapping("/register")
    public String register(@RequestBody User user){
        userRepository.save(user);
        return "User Registered Successfully in database";
    }

    //Get all users
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        List<User> user = userRepository.findAll();
        if(user.isEmpty()){
            return ResponseEntity.ok("No user registered yet");
        }
        else{
            return ResponseEntity.ok(user);
        }
    }

    //Find user by userid
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userRepository.findById(id).orElse(null);
    }

    

    
}
