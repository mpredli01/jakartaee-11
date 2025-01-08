package org.redlich.jwtbridge.users;

import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class UserFacade {
    public static Map<String,  User> users = new HashMap<>();

    static {
        users.put("admin", new User("admin", "123456", List.of("admin")));
        users.put("Luis", new User("Luis", "123456", List.of("user")));
        users.put("Mike", new User("Mike", "password", List.of("admin")));
        }

    public User getUser(String userName) {
        return users.get(userName);
        }

    public boolean verifyUserPassword(String userName, String password) {
        User user = getUser(userName);
        return user.password.equals(password);
        }
    }
