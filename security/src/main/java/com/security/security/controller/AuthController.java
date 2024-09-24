package com.security.security.controller;

import com.security.security.dto.UserLoginDto;
import com.security.security.model.AppUser;
import com.security.security.service.AuthService;
import com.security.security.service.CustomUserDetailsServiceImpl;
import com.security.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AppUser userData){
        Object user = authService.registerNewUser(userData);
        if (user instanceof String) {  // If the result is a message indicating failure
            return new ResponseEntity<>(user, HttpStatus.CONFLICT);  // Return 409 Conflict
        } else {
            return new ResponseEntity<>(user, HttpStatus.CREATED);  // Return 201 Created
        }
    }

    @PostMapping("/userlogin")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDto credentials) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(credentials.getEmail());
            String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);

        }
        catch (Exception e) {
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
}
