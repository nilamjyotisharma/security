package com.security.security.service;
import com.security.security.model.AppUser;
import com.security.security.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AppUserRepository appUserRepository;
    public Object registerNewUser(AppUser userData) {
        AppUser newUser = new AppUser();
        AppUser checkUser = appUserRepository.findByEmail(userData.getEmail());
        AppUser checkUserByPhone = appUserRepository.findByPhone(userData.getPhone());
        if(checkUser != null || checkUserByPhone != null){
            return "Email or Phone number already exist!";
        }
        else{
            newUser.setName(userData.getName());
            newUser.setEmail(userData.getEmail());
            newUser.setPhone(userData.getPhone());
            newUser.setPassword(passwordEncoder.encode(userData.getPassword()));
            newUser.setRole(userData.getRole());

            appUserRepository.save(newUser);

            return newUser;
        }

    }

}
