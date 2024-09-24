package com.security.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class UserController {
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/helloUser")
    public String helloUser(){
        return "Hello User";
    }
}
