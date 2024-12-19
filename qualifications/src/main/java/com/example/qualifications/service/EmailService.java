package com.example.qualifications.service;

import com.example.qualifications.configuration.EmailConfiguration;
import lombok.RequiredArgsConstructor;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private EmailConfiguration emailConfiguration;

    @Value("${front.url}")
    private String frontendUrl;

    @Value("classpath:static/mail-order.html")
    private Resource orderTemplate;

    public void sendActivation(String mail,String uuid){
        try{
            String html = Files.toString(orderTemplate.getFile(), Charsets.UTF_8);
            html = html.replace("https://google.com",frontendUrl+"/zamowienia/"+uuid);
            emailConfiguration.sendMail(mail, html,"Utworzono zamówienie",true);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}

