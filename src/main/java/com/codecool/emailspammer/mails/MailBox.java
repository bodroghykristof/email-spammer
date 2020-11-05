package com.codecool.emailspammer.mails;


import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

public class MailBox {

    Queue<Mail> mails = new ArrayDeque<>();

    public Optional<Mail> getNextMail() {
        return Optional.ofNullable(mails.peek());
    }

    public void markMailAsDelivered() {
        mails.poll();
    }

    public void addMail(Mail mail) {
        mails.add(mail);
    }
}
