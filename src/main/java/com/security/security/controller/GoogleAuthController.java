package com.security.security.controller;


import com.security.security.service.GoogleAuthService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {
        "http://localhost:3000",
        "http://10.177.48.220:3000"
})

@RestController
@RequestMapping("/auth/google")
public class GoogleAuthController {

    @Autowired
    private GoogleAuthService googleAuthService;


    @GetMapping("/callback")
    public ResponseEntity<String> handleGoogleCallback(@RequestParam String authCode, @RequestParam Boolean isPhoneRequired){
        System.out.println("#############################inside######################################");
        return googleAuthService.handleGoogleAuth(authCode, isPhoneRequired);
    }
}
