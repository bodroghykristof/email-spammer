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

    public MailHandler(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public void sendMail(String messageBody) {

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
            Message message = prepareMessage(session, messageBody);
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException e) {
            System.out.println("Error while sending email");
        }
    }

    private Message prepareMessage(Session session, String messageBody) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(serverEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(clientEmail));
        message.setSubject("Spammer action");
        message.setContent(composeMessage(messageBody), "text/html");
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
