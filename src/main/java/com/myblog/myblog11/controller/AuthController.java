package com.myblog.myblog11.controller;

import com.myblog.myblog11.entity.User;
import com.myblog.myblog11.payload.SignUpDto;
import com.myblog.myblog11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;

@RestController
@RequestMapping("/auth/login")
public class AuthController {
    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    //http:localhost:8080/auth/login/signup
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser (@RequestBody SignUpDto signUpDto) {
        if (repository.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("User name already exists!!!", HttpStatus.BAD_REQUEST);
        }
        if (repository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email already exists!!!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setEmail(signUpDto.getEmail());
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        repository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
