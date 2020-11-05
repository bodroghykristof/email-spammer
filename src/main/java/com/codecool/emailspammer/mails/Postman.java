package com.codecool.emailspammer.mails;

import com.codecool.emailspammer.network.MailHandler;

import java.util.Optional;

public class Postman implements Runnable {

    private final MailBox mailBox;
    private final MailHandler mailHandler;

    public Postman(MailBox mailBox, MailHandler mailHandler) {
        this.mailBox = mailBox;
        this.mailHandler = mailHandler;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            Optional<Mail> mail = mailBox.getNextMail();
            if (mail.isPresent() && mailHandler.sendMail(mail.get())) {
                mailBox.markMailAsDelivered();
            }
        }

    }

}
