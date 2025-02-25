/*
 * Copyright (c), Eclipse Foundation, Inc. and its licensors.
 *
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v1.0, which is available at
 * https://www.eclipse.org/org/documents/edl-v10.php
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package org.redlich.mail;

import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import jakarta.mail.internet.AddressException;

/**
 * <p>MailApp class.</p>
 *
 * @author mpredli01
 */
public class MailApp {

    MailApp() {
        }

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects
     */
    public static void main(String[] args) { // throws MessagingException {
        MailApp app = new MailApp();
        String title = "[APP] Welcome to the Jakarta Mail Demo Application";
        app.displayTitle(title);

        final String to = "mike@redlich.net";
        final String from = "mpredli@gmail.com";
        final String username = "<email address>";
        final String password = "<password>";

        final String host = "smtp.gmail.com";
        final int port = 587;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        
        System.out.println("[APP] " + auth);
        System.out.println("[APP] To: " + to);
        System.out.println("[APP] From: " + from);
        System.out.println("[APP] Host: " + host);

        Session session = Session.getInstance(props, auth);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Groovy email");
            message.setText("Test message");
            Transport.send(message);
            System.out.println("[APP] Email sent successfully");
            }
        catch(AuthenticationFailedException exception) {
            System.out.println("[EXCEPTION] AuthenticationFailedException" + exception);
            }
        catch(AddressException exception) {
            System.out.println("[EXCEPTION] AddressException" + exception);
            }
        catch(MessagingException exception) {
            System.out.println("[EXCEPTION] MessagingException" + exception);
            }
        }

    /**
     * <p>displayTitle.</p>
     *
     * @param title a {@link java.lang.String} object
     */
    public void displayTitle(String title) {
        int length = title.length();
        System.out.print("[APP] ");
        for(int i = 0; i < length; ++i) {
            System.out.print("-");
            }
        System.out.println();
        System.out.println(title);
        System.out.print("[APP] ");
        for(int i = 0; i < length; ++i) {
            System.out.print("-");
            }
        System.out.println();
        }
    }
