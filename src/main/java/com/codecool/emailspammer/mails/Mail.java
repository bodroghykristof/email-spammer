package com.codecool.emailspammer.mails;

public class Mail {

    private final String email;
    private final String message;

    public Mail(String email, String message) {
        this.email = email;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }
}
