package com.prueba.user_api.Service;

import com.prueba.user_api.Dto.LoginRequest;
import com.prueba.user_api.Dto.LoginResponse;
import com.prueba.user_api.Model.User;
import com.prueba.user_api.Repository.UserRepository;
import com.prueba.user_api.Util.EncryptionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AuthServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldLoginSuccessfully() {

        LoginRequest request = new LoginRequest();
        request.setTaxId("ABC123");
        request.setPassword("1234");

        User user = new User();
        user.setTaxId("ABC123");
        user.setPassword(EncryptionUtil.encrypt("1234"));

        when(userRepository.findAll()).thenReturn(List.of(user));

        LoginResponse response = authService.login(request);

        assertTrue(response.isSuccess());
        assertEquals("Login successful", response.getMessage());
    }

    @Test
    void shouldFailLogin() {

        LoginRequest request = new LoginRequest();
        request.setTaxId("ABC123");
        request.setPassword("wrong");

        User user = new User();
        user.setTaxId("ABC123");
        user.setPassword(EncryptionUtil.encrypt("1234"));

        when(userRepository.findAll()).thenReturn(List.of(user));

        LoginResponse response = authService.login(request);

        assertFalse(response.isSuccess());
    }
}
