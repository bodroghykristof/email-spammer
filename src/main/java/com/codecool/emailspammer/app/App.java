package com.codecool.emailspammer.app;

import com.codecool.emailspammer.io.ConsoleLogger;
import com.codecool.emailspammer.io.Logger;
import com.codecool.emailspammer.mails.Mail;
import com.codecool.emailspammer.network.MailHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws InterruptedException {

        Logger logger = new ConsoleLogger();
        logger.printTitle("Welcome To Email Spammer");
        MailHandler mailHandler = new MailHandler(System.getenv("EMAIL"), System.getenv("EMAIL_PASSWORD"));
        ExecutorService executor = Executors.newCachedThreadPool();

        while (true) {

            String clientEmail = logger.askEmailAddress();
            String message = logger.askMessage();
            Mail mail = new Mail(clientEmail, message);
            if (message.toLowerCase().equals("quit")) break;
            executor.submit(() -> mailHandler.sendMail(mail));

        }

        executor.shutdown();
        executor.awaitTermination(3, TimeUnit.SECONDS);

    }

}
