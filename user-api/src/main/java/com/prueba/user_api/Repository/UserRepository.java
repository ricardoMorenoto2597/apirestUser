package com.prueba.user_api.Repository;

import com.prueba.user_api.Model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();
    public List<User> findAll() {
        return users;
    }
    public void save(User user) {
        users.add(user);
    }

    public User findById(UUID id) {

        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }

        return null;
    }

    public void deleteById(UUID id) {

        users.removeIf(user -> user.getId().equals(id));

    }
}
