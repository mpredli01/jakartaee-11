package org.redlich.jwtbridge.users;

import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>UserFacade class.</p>
 *
 * @author mpredli01
 */
@Singleton
public class UserFacade {
    /** Constant <code>users</code> */
    public static Map<String,  User> users = new HashMap<>();

    static {
        users.put("admin", new User("admin", "123456", List.of("admin")));
        users.put("Luis", new User("Luis", "123456", List.of("user")));
        users.put("Mike", new User("Mike", "password", List.of("admin")));
        }

    /**
     * <p>getUser.</p>
     *
     * @param userName a {@link java.lang.String} object
     * @return a {@link org.redlich.jwtbridge.users.User} object
     */
    public User getUser(String userName) {
        return users.get(userName);
        }

    /**
     * <p>verifyUserPassword.</p>
     *
     * @param userName a {@link java.lang.String} object
     * @param password a {@link java.lang.String} object
     * @return a boolean
     */
    public boolean verifyUserPassword(String userName, String password) {
        User user = getUser(userName);
        return user.password.equals(password);
        }
    }
