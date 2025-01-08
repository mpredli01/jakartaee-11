package org.redlich.jwtbridge.users;

import java.util.Collections;
import java.util.List;

public class User {
    String name;
    String password;
    List<String> roles;

    public User(String name, String password, List<String> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
        }

    public List<String> getRoles() {
        return Collections.unmodifiableList(this.roles);
        }
    }
