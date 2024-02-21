package com.example.auth.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class AuthResponseTest {

    @Test
    void constructorShouldSetTimestampMessageAndCode(){
        //Given
        Code testCode = Code.SUCCESS;

        //When
        AuthResponse authResponse = new AuthResponse(testCode);

        //Then
        assertThat(authResponse.getTimestamp()).isNotNull();
        assertThat(authResponse.getMessage()).isEqualTo("Operacja zakończona sukcesem");
        assertThat(authResponse.getCode()).isEqualTo(testCode);
    }

    @Test
    void gettersShouldReturnCorrectValues() {
        // Given
        Code testCode = Code.PERMIT;
        AuthResponse authResponse = new AuthResponse(testCode);

        // Then
        assertThat(authResponse.getTimestamp()).isNotNull();
        assertThat(authResponse.getMessage()).isEqualTo("Przyznano dostep");
        assertThat(authResponse.getCode()).isEqualTo(testCode);
    }

    @Test
    void userShouldNotExist() {
        // Given
        Code testCode = Code.A6;
        AuthResponse authResponse = new AuthResponse(testCode);

        // Then
        assertThat(authResponse.getTimestamp()).isNotNull();
        assertThat(authResponse.getMessage()).isEqualTo("Użytkownik nie istnieje");
        assertThat(authResponse.getCode()).isEqualTo(testCode);
    }
}
