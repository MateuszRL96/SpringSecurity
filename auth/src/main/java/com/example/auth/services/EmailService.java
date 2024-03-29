package com.example.auth.services;


import com.example.auth.config.EmailConfiguration;
import com.example.auth.entity.User;
import com.google.common.base.Charsets;
import lombok.RequiredArgsConstructor;

import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final EmailConfiguration emailConfiguration;
    @Value("${front.url}")
    private String frontendUrl;

    @Value("classpath:static/mail-active.html")
    private Resource activeTemplate;

    public void sendActivation(User user) {
        try {
            String html = Files.toString(activeTemplate.getFile(), Charsets.UTF_8);
            html = html.replace("https://google.com", frontendUrl+"/aktywuj/"+user.getUuid());
            emailConfiguration.sendMail(user.getEmail(), html, "Aktywacja konta",true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendPasswordRecovery(User user, String uid) {
        try{
            String html = Files.toString(activeTemplate.getFile(), Charsets.UTF_8);
            html = html.replace("https://google.com", frontendUrl+"/odzyskaj-haslo/"+uid);
            emailConfiguration.sendMail(user.getEmail(), html, "Odzyskane haslo",true);
        }catch( IOException e){
            throw new RuntimeException(e);
        }
    }
}
