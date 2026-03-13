package com.prueba.user_api.Controller;

import com.prueba.user_api.Dto.LoginRequest;
import com.prueba.user_api.Dto.LoginResponse;
import com.prueba.user_api.Model.User;
import com.prueba.user_api.Service.AuthService;
import com.prueba.user_api.Service.UserService;
import jakarta.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("AuthController")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }




}
