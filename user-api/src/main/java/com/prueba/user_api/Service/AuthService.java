package com.prueba.user_api.Service;

import com.prueba.user_api.Dto.LoginRequest;
import com.prueba.user_api.Dto.LoginResponse;
import com.prueba.user_api.Model.User;
import com.prueba.user_api.Repository.UserRepository;
import com.prueba.user_api.Util.EncryptionUtil;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest request) {

        for (User user : userRepository.findAll()) {

            if (user.getTaxId().equals(request.getTaxId())) {

                String encryptedPassword =
                        EncryptionUtil.encrypt(request.getPassword());

                if (user.getPassword().equals(encryptedPassword)) {

                    return new LoginResponse(true, "Login successful");

                }
            }

        }

        return new LoginResponse(false, "Invalid credentials");
    }

}
