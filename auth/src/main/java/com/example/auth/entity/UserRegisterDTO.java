package com.example.auth.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Builder
@Getter
public class UserRegisterDTO {
    private String login;
    private String email;
    private String password;
    private Role role;


}
