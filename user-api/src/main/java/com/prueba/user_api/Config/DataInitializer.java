package com.prueba.user_api.Config;

import com.prueba.user_api.Model.Address;
import com.prueba.user_api.Model.User;
import com.prueba.user_api.Repository.UserRepository;

import com.prueba.user_api.Util.EncryptionUtil;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DataInitializer {
    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostConstruct
    public void init() {

        User user1 = new User(
                UUID.randomUUID(),
                "ricardo@mail.com",
                "ricardo",
                "+15555555555",
                EncryptionUtil.encrypt("UnoDos3*"),
                "AARR990101XXX",
                "01-01-2026 00:00:00",
                List.of(
                        new Address(1,"workaddress","street No. 1","UK"),
                        new Address(2,"homeaddress","street No. 2","AU")
                )
        );

        User user2 = new User(
                UUID.randomUUID(),
                "paulina@mail.com",
                "paulina",
                "+15555555556",
                EncryptionUtil.encrypt("UnoDos3*"),
                "AARR990101YYY",
                "01-01-2026 00:00:00",
                List.of( new Address(1,"Tijuana","street No. 1","MEX"),
                        new Address(2,"Guadalajara","street No. 2","MEX"))
        );

        User user3 = new User(
                UUID.randomUUID(),
                "daniela@mail.com",
                "daniela",
                "+15555555557",
                EncryptionUtil.encrypt("UnoDos3*"),
                "AARR990101ZZZ",
                "01-01-2026 00:00:00",
                List.of( new Address(1,"monterrey","street No. 1","MEX"),
                        new Address(2,"chiapas","street No. 2","MEX")
                )
        );

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }


}
