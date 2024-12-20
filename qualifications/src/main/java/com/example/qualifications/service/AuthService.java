package com.example.qualifications.service;

import com.example.qualifications.entity.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import jakarta.servlet.http.Cookie;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private RestTemplate restTemplate;

    public UserRegisterDTO getUserDetails(List<Cookie> cookie)  {
        HttpHeaders httpHeaders = new HttpHeaders();
        StringBuilder cookieString = new StringBuilder();
        cookie.forEach(value->{
            cookieString.append(value.getName()).append("=").append(value.getValue()).append(";");
        });
        if (cookieString.length() <= 0) return null;
        cookieString.deleteCharAt(cookieString.length()-1);
        httpHeaders.add("Cookie",cookieString.toString());
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);
        try{
            String auth_url = "http://localhost:8888/api/v1/auth/auto-login";
            ResponseEntity<UserRegisterDTO> response = restTemplate.exchange(auth_url, HttpMethod.GET,requestEntity, UserRegisterDTO.class);
            return response.getStatusCode().isError() ? null : response.getBody();
        }catch (HttpClientErrorException e){
            return null;
        }
    }
}

