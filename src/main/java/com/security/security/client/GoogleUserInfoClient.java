package com.security.security.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "google-token-info", url = "https://oauth2.googleapis.com")
public interface GoogleUserInfoClient {
    @GetMapping("/tokeninfo")
    Map<String, Object> getTokenInfo(@RequestParam("id_token") String idToken);
}
