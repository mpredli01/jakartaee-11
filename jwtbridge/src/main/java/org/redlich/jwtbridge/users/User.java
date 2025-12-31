package org.redlich.jwtbridge.users;

import java.util.Collections;
import java.util.List;

/**
 * <p>User class.</p>
 *
 * @author mpredli01
 */
public class User {
    String name;
    String password;
    List<String> roles;

    /**
     * <p>Constructor for User.</p>
     *
     * @param name a {@link java.lang.String} object
     * @param password a {@link java.lang.String} object
     * @param roles a {@link java.util.List} object
     */
    public User(String name, String password, List<String> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
        }

    /**
     * <p>Getter for the field <code>roles</code>.</p>
     *
     * @return a {@link java.util.List} object
     */
    public List<String> getRoles() {
        return Collections.unmodifiableList(this.roles);
        }
    }
