package com.example.auth.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

public class UserRegisterDTOTest {

    @Test
    void testValidUserRegisterDTO() {
        UserRegisterDTO userRegisterDTO = UserRegisterDTO.builder()
                .login("testUser")
                .email("test@example.com")
                .password("testPassword123")
                .role(Role.USER)
                .build();

        Errors errors = new BeanPropertyBindingResult(userRegisterDTO, "userRegisterDTO");
        assertFalse(errors.hasErrors());
    }

}
