package com.security.security.service;
import com.security.security.model.AppUser;
import com.security.security.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AppUserRepository appUserRepository;
    public AppUser registerNewUser(AppUser userData) {
        AppUser newUser = new AppUser();
        newUser.setName(userData.getName());
        newUser.setEmail(userData.getEmail());
        newUser.setPhone(userData.getPhone());
        newUser.setPassword(passwordEncoder.encode(userData.getPassword()));
        newUser.setRole(userData.getRole());

        appUserRepository.save(newUser);

        return newUser;
    }

}
