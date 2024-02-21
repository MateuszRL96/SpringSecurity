package com.example.auth.entity;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static com.example.auth.entity.Role.USER;

public class UserTest {

    @Test
    void generateUUidShouldGenerateValidUuid(){
        User user = new User();
        user.generateUuid();

        assertNotNull(user.getUuid());
        assertFalse(user.getUuid().isEmpty());
    }

    @Test
    void getAuthoritiesShouldReturnRoleAsGrantedAuthority(){
        User user = new User();
        user.setRole(USER);

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority(USER.name())));
    }




}
