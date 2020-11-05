package com.codecool.emailspammer.io;

import java.util.Scanner;

public class ConsoleLogger implements Logger {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void printTitle(String title) {
        System.out.println("--- " + title.toUpperCase() + " ---");
    }

    @Override
    public String askEmailAddress() {
        System.out.println("Please enter the target email address!");
        String input = "";
        while (true) {
            input = scanner.nextLine();
            if (!input.matches("^[^@.]+@[^@.]+\\.[^@.]+$")) {
                System.out.println("Invalid email. Please use the following format: example@example.com");
            } else return input;
        }
    }

    @Override
    public String askMessage() {
        System.out.println("Please enter the message! Escape with typing 'quit'");
        String input = "";
        while (true) {
            input = scanner.nextLine();
            if (input.length() == 0) System.out.println("Empty message will not be sent");
            else return input;
        }
    }
}
