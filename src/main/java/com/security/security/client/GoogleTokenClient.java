package com.security.security.client;
import com.security.security.config.FeignClientConfig;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "google-oauth", url = "https://oauth2.googleapis.com", configuration = FeignClientConfig.class)
public interface GoogleTokenClient {
    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/x-www-form-urlencoded")

    Map<String, Object> exchangeToken(@RequestBody MultiValueMap<String, String> params);

}


