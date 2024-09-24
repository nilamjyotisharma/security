package com.security.security.service;

import com.security.security.controller.UserRole;
import com.security.security.model.AppUser;
import com.security.security.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Retrieve user from the repository
        AppUser user = appUserRepository.findByEmail(email);
        if (user != null) {
            // Convert the user's role to a string authority
            String role = user.getRole().getRole();  // Get the specific role for the user

            // Build the UserDetails object with only the user's specific role
            UserDetails userDetails = User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .authorities(new SimpleGrantedAuthority(role))  // Use the specific role
                    .build();

            return userDetails;
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}



