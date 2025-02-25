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

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.AuthenticationFailedException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Properties;

import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * <p>MailResource class.</p>
 *
 * @author mpredli01
 */
@Path("mail")
public class MailResource {

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Inject
    @ConfigProperty(name = "to")
    String to;

    @Inject
    @ConfigProperty(name = "from")
    String from;

    @Inject
    @ConfigProperty(name = "username")
    String username;

    @Inject
    @ConfigProperty(name = "password")
    String password;

    @Inject
    MailService service;

    /**
     * <p>mail.</p>
     *
     * @return a {@link java.lang.String} object
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String mail() {

        StringBuilder builder = new StringBuilder();
        builder.append(this.message);
        builder.append("\n\n");

        // final String to = this.to;
        // final String from = this.from;
        // final String username = "mpredli@gmail.com";
        // final String password = "<password>";

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

        builder.append(auth);
        builder.append("\n");
        builder.append("To: " + this.to);
        builder.append("\n");
        builder.append("From: " + this.from);
        builder.append("\n");
        builder.append("Username: " + this.username);
        builder.append("\n");

        Session session = Session.getInstance(props, auth);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(this.from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.to));
            message.setSubject("Groovy email");
            message.setText("Test message");
            Transport.send(message);
            builder.append("Email successfully sent");
            }
        catch(AuthenticationFailedException exception) {
            builder.append("[EXCEPTION] AuthenticationFailedException:");
            builder.append("\n");
            builder.append(exception);
            builder.append("\n");
            }
        catch(AddressException exception) {
            builder.append("[EXCEPTION ] AddressException:");
            builder.append("\n");
            builder.append(exception);
            builder.append("\n");
            }
        catch(MessagingException exception) {
            builder.append("[EXCEPTION] MessagingException:");
            builder.append(exception);
            builder.append("\n");
            }

        builder.append(service.message());
        builder.append("\n");
        return builder.toString();
        }
    }
