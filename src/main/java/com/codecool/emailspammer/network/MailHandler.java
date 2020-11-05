package com.codecool.emailspammer.network;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class MailHandler {

    private final String serverEmail = System.getenv("EMAIL");
    private final String serverPassword = System.getenv("EMAIL_PASSWORD");
    private final String clientEmail;
    private final String message;

    public MailHandler(String clientEmail, String message) {
        this.clientEmail = clientEmail;
        this.message = message;
    }

    public void sendMail() {

        System.out.println("Trying to send message");
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
            System.out.println("Preparing message");
            Message message = prepareMessage(session);
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException e) {
            System.out.println("Error while sending email");
        }
    }

    private Message prepareMessage(Session session) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(serverEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(clientEmail));
        message.setSubject("Spammer action");
        message.setContent(composeMessage(), "text/html");
        return message;
    }

    private String composeMessage() {
        return String.format(
                "<p>Message: %s</p>" +
                "<p>Date: %s</p>" +
                "<p>From: %s</p>",
                message, currentDate(), serverEmail);
    }

    private Object currentDate() {
        SimpleDateFormat dnt = new SimpleDateFormat("yyyy.MM.dd.");
        Date date = new Date();
        return dnt.format(date);
    }

}
