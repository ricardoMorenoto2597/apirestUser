package com.prueba.user_api.Controller;

import com.prueba.user_api.Dto.UserRequest;
import com.prueba.user_api.Dto.UserResponse;
import com.prueba.user_api.Service.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    /*inyeccion de servicios*/
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getUsers(@RequestParam(required = false) String sortedBy, @RequestParam(required = false) String filter) {
        return userService.getUsers(sortedBy, filter);
    }
    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);

    }
    @PatchMapping("/{id}")
    public UserResponse updateUser(@PathVariable UUID id, @RequestBody Map<String, Object> updates) {
        return userService.updateUser(id, updates);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);

    }

}
