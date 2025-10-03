package com.fizzahmajaz.trading.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fizzahmajaz.trading.entity.User;
import com.fizzahmajaz.trading.repository.UserRepository;
import com.fizzahmajaz.trading.security.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User request){
    User user = userRepository.findByUsername(request.getUsername());
    if(user == null) return ResponseEntity.badRequest().body("User not found");
    if(!user.getPassword().equals(request.getPassword())) //check password
    return ResponseEntity.badRequest().body("Invalid credentials");

    String token = jwtUtil.generateToken(user.getUsername());
    return ResponseEntity.ok(token);
    
}

    
}
