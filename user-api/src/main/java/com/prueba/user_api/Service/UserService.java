package com.prueba.user_api.Service;

import com.prueba.user_api.Dto.AddressRequest;
import com.prueba.user_api.Dto.UserRequest;
import com.prueba.user_api.Dto.UserResponse;
import com.prueba.user_api.Model.Address;
import com.prueba.user_api.Model.User;
import com.prueba.user_api.Repository.UserRepository;
import com.prueba.user_api.Util.EncryptionUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getUsers(String sortedBy, String filter) {

        List<User> users = userRepository.findAll();

        users = applyFilter(users, filter);

        applySorting(users, sortedBy);

        return users.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse createUser(UserRequest request) {

        boolean exists = userRepository.findAll()
                .stream()
                .anyMatch(u -> u.getTaxId().equals(request.getTaxId()));

        if (exists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "tax_id already exists"
            );
        }

        User user = new User();

        user.setId(UUID.randomUUID());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPhone(request.getPhone());

        user.setPassword(
                EncryptionUtil.encrypt(request.getPassword())
        );

        user.setTaxId(request.getTaxId());

        user.setCreatedAt(getMadagascarTime());

        user.setAddress(
                convertAddresses(request.getAddresses())
        );

        userRepository.save(user);

        return toResponse(user);
    }

    public UserResponse updateUser(UUID id, Map<String, Object> updates) {

        User user = userRepository.findById(id);

        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User not found"
            );
        }

        updates.forEach((key, value) -> {

            switch (key) {

                case "email":
                    user.setEmail((String) value);
                    break;

                case "name":
                    user.setName((String) value);
                    break;

                case "phone":
                    user.setPhone((String) value);
                    break;

            }

        });

        return toResponse(user);
    }

    public void deleteUser(UUID id) {


        User user = userRepository.findById(id);

        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User not found"
            );
        }

        userRepository.deleteById(id);

    }

    private List<User> applyFilter(List<User> users, String filter) {

        if (filter == null || filter.isEmpty()) {
            return users;
        }

        String[] parts = filter.split("\\+");

        if (parts.length != 3) {
            return users;
        }

        String field = parts[0];
        String operator = parts[1];
        String value = parts[2];

        return users.stream()
                .filter(user -> matchFilter(user, field, operator, value))
                .collect(Collectors.toList());
    }

    private boolean matchFilter(User user, String field, String operator, String value) {

        String fieldValue = "";

        switch (field) {

            case "id":
                fieldValue = user.getId().toString();
                break;

            case "email":
                fieldValue = user.getEmail();
                break;

            case "name":
                fieldValue = user.getName();
                break;

            case "phone":
                fieldValue = user.getPhone();
                break;

            case "tax_id":
                fieldValue = user.getTaxId();
                break;

            case "created_at":
                fieldValue = user.getCreatedAt();
                break;
        }

        switch (operator) {

            case "co":
                return fieldValue.contains(value);

            case "eq":
                return fieldValue.equals(value);

            case "sw":
                return fieldValue.startsWith(value);

            case "ew":
                return fieldValue.endsWith(value);
        }

        return false;
    }

    private void applySorting(List<User> users, String sortedBy) {

        if (sortedBy == null || sortedBy.isEmpty()) {
            return;
        }

        switch (sortedBy) {

            case "id":
                users.sort(Comparator.comparing(User::getId));
                break;

            case "email":
                users.sort(Comparator.comparing(User::getEmail));
                break;

            case "name":
                users.sort(Comparator.comparing(User::getName));
                break;

            case "phone":
                users.sort(Comparator.comparing(User::getPhone));
                break;

            case "tax_id":
                users.sort(Comparator.comparing(User::getTaxId));
                break;

            case "created_at":
                users.sort(Comparator.comparing(User::getCreatedAt));
                break;
        }
    }

    private List<Address> convertAddresses(List<AddressRequest> requests) {

        if (requests == null) return new ArrayList<>();

        return requests.stream()
                .map(a -> new Address(
                        a.getId(),
                        a.getName(),
                        a.getStreet(),
                        a.getCountryCode()
                ))
                .collect(Collectors.toList());

    }

    private UserResponse toResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPhone(),
                user.getTaxId(),
                user.getCreatedAt(),
                user.getAddress()
        );
    }

    private String getMadagascarTime() {

        ZonedDateTime now = ZonedDateTime.now(
                ZoneId.of("Indian/Antananarivo")
        );

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        return now.format(formatter);

    }
}
