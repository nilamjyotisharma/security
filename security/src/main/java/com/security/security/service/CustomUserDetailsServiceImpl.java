package com.security.security.service;

import com.security.security.model.AppUser;
import com.security.security.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByEmail(email);
        if(user != null){
            UserDetails userDetails = User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().getUserRoleType().toArray(new String[0]))
                    .build();
            return userDetails;
        }
        else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}
