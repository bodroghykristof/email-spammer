package com.codecool.emailspammer.app;

import com.codecool.emailspammer.io.ConsoleLogger;
import com.codecool.emailspammer.io.Logger;
import com.codecool.emailspammer.network.MailHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws InterruptedException {

        Logger logger = new ConsoleLogger();
        logger.printTitle("Welcome To Email Spammer");
        String clientEmail = logger.askEmailAddress();
        MailHandler mailHandler = new MailHandler(clientEmail);
        ExecutorService executor = Executors.newCachedThreadPool();

        while (true) {

            String message = logger.askMessage();
            if (message.toLowerCase().equals("quit")) break;
            executor.submit(() -> mailHandler.sendMail(message));
        }

        executor.shutdown();
        executor.awaitTermination(3, TimeUnit.SECONDS);

    }

}
