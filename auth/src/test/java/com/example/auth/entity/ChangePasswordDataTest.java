package com.example.auth.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ChangePasswordDataTest {

    @Test
    void constructorShouldSetPasswordAndUid() {
        // Given
        String testPassword = "newPassword123";
        String testUid = "user123";

        // When
        ChangePasswordData changePasswordData = new ChangePasswordData(testPassword, testUid);

        // Then
        assertThat(changePasswordData.getPassword()).isEqualTo(testPassword);
        assertThat(changePasswordData.getUid()).isNotNull();
    }

    @Test
    void settersAndGettersShouldWorkCorrectly() {
        // Given
        ChangePasswordData changePasswordData = new ChangePasswordData("oldPassword", "user456");

        // When
        changePasswordData.setPassword("newPassword");
        changePasswordData.setUid("user789");

        // Then
        assertThat(changePasswordData.getPassword()).isEqualTo("newPassword");
        assertThat(changePasswordData.getUid()).isEqualTo("user789");
    }
}
