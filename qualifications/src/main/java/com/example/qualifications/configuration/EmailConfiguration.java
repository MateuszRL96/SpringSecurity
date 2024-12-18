package com.example.qualifications.configuration;

import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class EmailConfiguration {

    private final String email;
    private final String password;
    private final String auth;
    private Session session;

}
