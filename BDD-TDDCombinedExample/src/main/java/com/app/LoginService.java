package com.app;

public class LoginService {

    private static final String VALID_USERNAME = "username";
    private static final String VALID_PASSWORD = "password";

    public LoginResponse login(String username, String password) {

        if (username == null || password == null) {
            return new LoginResponse(false, "Username or password cannot be null");
        }

        if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
            return new LoginResponse(true, "Login successful");
        }

        return new LoginResponse(false, "Login and/or password are wrong.");
    }
}
