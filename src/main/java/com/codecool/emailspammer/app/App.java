package com.codecool.emailspammer.app;

import com.codecool.emailspammer.io.ConsoleLogger;
import com.codecool.emailspammer.io.Logger;
import com.codecool.emailspammer.mails.Mail;
import com.codecool.emailspammer.mails.MailBox;
import com.codecool.emailspammer.mails.Postman;
import com.codecool.emailspammer.network.EmailPropertyValues;
import com.codecool.emailspammer.network.MailHandler;

import java.io.IOException;


public class App {

    public static void main(String[] args) throws InterruptedException, IOException {

        Logger logger = new ConsoleLogger();
        logger.printTitle("Welcome To Email Spammer");

        EmailPropertyValues values = new EmailPropertyValues();
        MailHandler mailHandler = new MailHandler(values.getEmail(), values.getPassword());
        MailBox mailBox = new MailBox();
        Postman postman = new Postman(mailBox, mailHandler);
        Thread delivering = new Thread(postman);
        delivering.start();

        while (true) {

            String clientEmail = logger.askEmailAddress();
            String message = logger.askMessage();
            if (message.toLowerCase().equals("quit")) break;

            Mail mail = new Mail(clientEmail, message);
            mailBox.addMail(mail);

        }

        delivering.interrupt();

    }

}
