package org.redlich.mail;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
// import jakarta.ws.rs.POST;
// import jakarta.ws.rs.Path;
import java.util.Date;
import java.util.Properties;

public class EmailService {

    public static void main(String[] args) {

        try {

            // create some properties and get the default Session
            final String message = "Greetings from the Jakarta Mail demo application!";
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", 587);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");
            
            final Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("mpredli@gmail.com", "Okt0b3r24@");
                }
            });

            // set this just to see some internal logging
            session.setDebug(true);

            // create a message
            final MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("mpredli@gmail.com"));
            final InternetAddress[] address = {new InternetAddress("mike@redlich.net")};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("JavaMail API test");
            msg.setSentDate(new Date());
            msg.setText(message, "UTF-8");

            Transport.send(msg);
            }
        catch (MessagingException exception) {
            System.out.println("EXCEPTION: Failed to send message: " + exception.getMessage());
            }
        }
    }
