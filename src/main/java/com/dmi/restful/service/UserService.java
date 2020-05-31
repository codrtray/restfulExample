package com.dmi.restful.service;

import com.dmi.restful.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserService {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "Jack", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(users.size() + 1);
        }
        users.add(user);
        return user;
    }

    public Optional<User> findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public boolean deleteById(int id) {
        return users.removeIf(user -> user.getId() == id);
    }

}