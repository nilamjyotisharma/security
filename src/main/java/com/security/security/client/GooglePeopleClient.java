package com.security.security.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "google-people-api", url = "https://people.googleapis.com")
public interface GooglePeopleClient {

    @GetMapping("/v1/people/me?personFields=phoneNumbers")
    Map<String, Object> getUserPhoneNumber(
            @RequestParam("personFields") String personFields,
            @RequestHeader("Authorization") String authorization
    );
}

