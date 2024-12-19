package com.example.qualifications.service;

import com.example.qualifications.entity.ListKoszykItemDTO;
import com.example.qualifications.exception.BasketDontExistException;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KoszykService {
    private RestTemplate restTemplate;
    private String KOSZYK_URL="http://localhost:8888/api/v1/basket";

    public ListKoszykItemDTO getKoszyk(Cookie value) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", value.getName()+"="+value.getValue());
        ResponseEntity<ListKoszykItemDTO> response;
        try{
            response =restTemplate.exchange(KOSZYK_URL,
                    HttpMethod.GET,
                    new HttpEntity<String>(headers),
                    ListKoszykItemDTO.class);
        }catch (HttpClientErrorException e){
            throw new BasketDontExistException("koszyk don't exist");
        }

        if (response.getStatusCode().isError()) throw new BasketDontExistException("koszyk don't exist");
        return response.getBody();
    }

    public void removeKoszyk(Cookie value,String uuid) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", value.getName()+"="+value.getValue());
        restTemplate.exchange(KOSZYK_URL+"?uuid="+uuid,
                HttpMethod.DELETE,
                new HttpEntity<String>(headers),
                String.class);
    }
}

