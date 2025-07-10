package com.trimblecars.carleaseservice.controller;

import com.trimblecars.carleaseservice.dto.UserDTO;
import com.trimblecars.carleaseservice.service.UserService;
import com.trimblecars.carleaseservice.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody  UserDTO userDTO){
        log.info("Attempting to register user : {}", userDTO.getUsername());
        try{
            userService.registerUser(userDTO);
            log.info("User registered successfully: {}", userDTO.getUsername());
            return ResponseEntity.ok("User registered successfully");
        } catch(Exception e){
            log.error("Error registering user: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser (@RequestBody UserDTO userDTO){
        log.info("Attempting to authenticate user: {}", userDTO.getUsername());
        try {
            userService.authenticate(userDTO.getUsername() , userDTO.getPassword());
            String token = jwtUtil.generateToken(userDTO.getUsername());
            log.info("User authenticated successfully: {}", userDTO.getUsername());
            return ResponseEntity.ok(token);
        }catch( Exception e){
            log.error("Error authenticateing user :{}", e.getMessage());
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
