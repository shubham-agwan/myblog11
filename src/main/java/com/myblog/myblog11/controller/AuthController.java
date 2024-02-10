package com.myblog.myblog11.controller;

import com.myblog.myblog11.entity.Role;
import com.myblog.myblog11.entity.User;
import com.myblog.myblog11.payload.SignUpDto;
import com.myblog.myblog11.repository.RoleRepository;
import com.myblog.myblog11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth/login")
public class AuthController {
    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository  roleRepository;

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

        /* here hardcoded value is removed and created the new column into signDto
        Role roles =  roleRepository.findByName("ROLE_ADMIN").get();
         */
        Role roles =  roleRepository.findByName(signUpDto.getRoleType()).get();

        // as Set <Role> is in entity class hence we need to assign role back to set
        Set <Role> convertRoleToSet= new HashSet <>();

        convertRoleToSet.add(roles);

        user.setRoles(convertRoleToSet);

        repository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
