package com.example.auth.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class LoginResponseTest {

    @Test
    void constructorShouldSetTimestampMessageAndCode() {
        // Given
        boolean testMessage = true;

        // When
        LoginResponse loginResponse = new LoginResponse(testMessage);

        // Then
        assertThat(loginResponse.getTimestamp()).isNotNull();
        assertThat(testMessage).isEqualTo(true);
        assertThat(loginResponse.getCode()).isEqualTo(Code.SUCCESS);

    }
}
