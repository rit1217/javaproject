package com.login;

import java.util.Properties;
import java.util.Random;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    private int random;
    private String to  = "phuritwarapat@gmail.com";
    private String from;

    public int randomInt(){
        Random random = new Random();
        return random.nextInt(8999)+1000;
    }

    public int getRandom(){
        return random;
    }

    public SendEmail(String recipientEmail){
        // Recipient's email ID needs to be mentioned.
        to = recipientEmail;
        // Sender's email ID needs to be mentioned
        from = "java.proj.shop.app@gmail.com";
        //generate random number
        random = randomInt();
    }

    public void run() {


        // Assuming you are sending email from through gmail's smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties props = System.getProperties();

        // Setup mail server
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "*");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("java.proj.shop.app@gmail.com", "shoppingjava");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Reset your password.");

            // Now set the actual message including a random number to verify yourself in the app
            message.setText("Please enter " + random + " in the Shopping app to reset your password.\n");

            System.out.println("Sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}

