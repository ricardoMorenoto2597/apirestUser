package com.prueba.user_api.Service;

import com.prueba.user_api.Dto.UserRequest;
import com.prueba.user_api.Dto.UserResponse;
import com.prueba.user_api.Model.User;
import com.prueba.user_api.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnUsers() {

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail("test@mail.com");
        user.setName("Ricardo");
        user.setPhone("+15555555555");
        user.setTaxId("ABC123");

        when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserResponse> result = userService.getUsers(null, null);

        assertEquals(1, result.size());
        assertEquals("Ricardo", result.get(0).getName());

        verify(userRepository).findAll();
    }

    @Test
    void shouldCreateUser() {

        UserRequest request = new UserRequest();
        request.setEmail("test@mail.com");
        request.setName("Ricardo");
        request.setPhone("+15555555555");
        request.setPassword("1234");
        request.setTaxId("ABC123");

        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        UserResponse response = userService.createUser(request);

        assertEquals("Ricardo", response.getName());
        assertEquals("ABC123", response.getTaxId());

        verify(userRepository).save(any(User.class));
    }

    @Test
    void shouldUpdateUser() {

        UUID id = UUID.randomUUID();

        User user = new User();
        user.setId(id);
        user.setName("Old Name");

        when(userRepository.findById(id)).thenReturn(user);

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "New Name");

        UserResponse response = userService.updateUser(id, updates);

        assertEquals("New Name", response.getName());
    }

    @Test
    void shouldDeleteUser() {

        UUID id = UUID.randomUUID();

        User user = new User();
        user.setId(id);

        when(userRepository.findById(id)).thenReturn(user);

        userService.deleteUser(id);

        verify(userRepository).deleteById(id);
    }

}
