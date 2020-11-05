package com.codecool.emailspammer.io;

public interface Logger {
    void printTitle(String title);

    String askEmailAddress();

    String askMessage();
}
