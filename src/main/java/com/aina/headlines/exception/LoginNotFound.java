package com.aina.headlines.exception;

public class LoginNotFound extends Exception {
    public LoginNotFound() {
        super("Please check email or your password");
    }
}
