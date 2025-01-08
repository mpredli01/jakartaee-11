package org.redlich.jwtbridge.api.token;

public class TokenResponse {
    private String token;

    public String getToken() {
        return token;
        }

    public void setToken(String token) {
        this.token = token;
        }

    public void displayToken(String token) {
        System.out.println("-----> " + token);
        }
    }
