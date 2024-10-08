package com.security.security.service;

import com.security.security.model.AppUser;
import com.security.security.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository userRepository;
    public AppUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
}
