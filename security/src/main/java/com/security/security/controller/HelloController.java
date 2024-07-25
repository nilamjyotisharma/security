package com.security.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
     @GetMapping("/hello")
     public String hello(){
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String userName = authentication.getName();
         return "Hello World";
     }
}
