package com.codecool.emailspammer.network;


import com.codecool.emailspammer.mails.Mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class MailHandler {

    private final String serverEmail;
    private final String serverPassword;

    public MailHandler(String serverEmail, String serverPassword) {
        this.serverEmail = serverEmail;
        this.serverPassword = serverPassword;
    }

    public boolean sendMail(Mail mail) {

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(serverEmail, serverPassword);
            }
        });

        try {
            Message message = prepareMessage(session, mail);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    private Message prepareMessage(Session session, Mail mail) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(serverEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getEmail()));
        message.setSubject("Spammer action");
        message.setContent(composeMessage(mail.getMessage()), "text/html");
        return message;
    }

    private String composeMessage(String messageBody) {
        return String.format(
                "<p>Message: %s</p>" +
                "<p>Date: %s</p>" +
                "<p>From: %s</p>",
                messageBody, currentDate(), serverEmail);
    }

    private Object currentDate() {
        SimpleDateFormat dnt = new SimpleDateFormat("yyyy.MM.dd.");
        Date date = new Date();
        return dnt.format(date);
    }

}
