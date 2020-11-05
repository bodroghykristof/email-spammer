package com.codecool.emailspammer.app;

import com.codecool.emailspammer.io.ConsoleLogger;
import com.codecool.emailspammer.io.Logger;
import com.codecool.emailspammer.network.MailHandler;

public class App {

    public static void main(String[] args) {

        Logger logger = new ConsoleLogger();
        logger.printTitle("Welcome To Email Spammer");
        String clientEmail = logger.askEmailAddress();
        MailHandler mailHandler = new MailHandler(clientEmail);

        while (true) {

            String message = logger.askMessage();
            if (message.toLowerCase().equals("quit")) break;
            mailHandler.sendMail(message);

        }

    }

}
