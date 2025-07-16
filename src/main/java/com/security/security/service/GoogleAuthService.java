package com.security.security.service;

import com.security.security.client.GooglePeopleClient;
import com.security.security.client.GoogleTokenClient;
import com.security.security.client.GoogleUserInfoClient;
import com.security.security.enums.UserRole;
import com.security.security.model.AppUser;
import com.security.security.repository.AppUserRepository;
import com.security.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class GoogleAuthService {

    @Autowired
    private GoogleTokenClient googleTokenClient;

    @Autowired
    private GoogleUserInfoClient googleUserInfoClient;

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private GooglePeopleClient googlePeopleClient;




    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    public ResponseEntity<String> handleGoogleAuth(String authCode, Boolean isPhoneRequired) {
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("code", authCode);
            params.add("client_id", clientId);
            params.add("client_secret", clientSecret);
            params.add("redirect_uri", "http://localhost:3000/oauth2callback");
            params.add("grant_type", "authorization_code");


            //Feign Client Call for EXCHANGE TOKEN
            Map<String, Object> tokenResponse = googleTokenClient.exchangeToken(params);

            String idToken = (String) tokenResponse.get("id_token");
            String accessToken = (String) tokenResponse.get("access_token");

            //Feign Client Call for EXTRACT USER INFORMATION FROM TOKEN
            Map<String, Object> userInfo = googleUserInfoClient.getTokenInfo(idToken);

            String email = (String) userInfo.get("email");
            String name = (String) userInfo.get("name");
            String profilePicture = (String) userInfo.get("picture");

            String phone = null;

            //Feign Client Call for EXTRACT USER PHONE NUMBER
            if(isPhoneRequired){

                Map<String, Object> peopleClientResponse = googlePeopleClient.getUserPhoneNumber("phoneNumbers", "Bearer " + accessToken);

                List<Map<String, Object>> phoneNumbers = (List<Map<String, Object>>) peopleClientResponse.get("phoneNumbers");

                if (phoneNumbers != null && !phoneNumbers.isEmpty()) {
                    Map<String, Object> firstNumber = phoneNumbers.get(0);
                    phone = (String) firstNumber.get("value"); // or "canonicalForm" for international format
                } else {
                    System.out.println("No phone number found in the response.");
                }

            }


            AppUser appUser = appUserRepository.findByEmail(email);

            if(appUser != null){
                appUser.setEmail(email);
                appUser.setName(name);
                appUser.setPhone(Objects.requireNonNullElse(phone, "00"));
                appUser.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
                appUser.setRole(UserRole.USER);
                appUser.setImageLink(profilePicture);
                appUserRepository.save(appUser);
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
                return new ResponseEntity<>(jwtToken, HttpStatus.OK);
            }else{
                AppUser user = new AppUser();
                user.setEmail(email);
                user.setName(name);
                user.setPhone(Objects.requireNonNullElse(phone, "00"));
                user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
                user.setRole(UserRole.USER);
                user.setImageLink(profilePicture);
                appUserRepository.save(user);
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
                return new ResponseEntity<>(jwtToken, HttpStatus.OK);
            }

        } catch (Exception e) {
//            log.error("Exception occurred while handleGoogleCallback ", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
