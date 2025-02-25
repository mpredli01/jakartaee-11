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
package org.redlich.concurrency;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.event.Observes;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

/**
 * Notify the clients so that they can refresh the log textarea
 *
 * @author mpredli01
 */
@Dependent
@ServerEndpoint("/wsinfo")
public class InfoEndpoint {

    private static final Logger log = Logger.getLogger("InfoEndpoint");
    /* Keep a list of clients */
    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    /**
     * <p>onOpen.</p>
     *
     * @param session a {@link jakarta.websocket.Session} object
     */
    @OnOpen
    public void onOpen(Session session) {
        log.info("[InfoEndpoint] Connection opened");
        sessions.add(session);
        }

    /**
     * <p>onClose.</p>
     *
     * @param session a {@link jakarta.websocket.Session} object
     */
    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        }

    /**
     * <p>onError.</p>
     *
     * @param session a {@link jakarta.websocket.Session} object
     * @param t a {@link java.lang.Throwable} object
     */
    @OnError
    public void onError(Session session, Throwable t) { }

    /**
     * <p>onMessage.</p>
     *
     * @param msg a {@link java.lang.String} object
     */
    @OnMessage
    public void onMessage(String msg) { }

    /* Observe the event fired from the EJB and notify clients.
     * The clients use JavaScript to make a JSF AJAX request to refresh
     * the log textarea. */
    /**
     * <p>pushAlert.</p>
     *
     * @param event a {@link java.lang.String} object
     */
    public static void pushAlert(@Observes String event) {
        for (Session s : sessions) {
            if (s.isOpen())
                try {
                    s.getBasicRemote().sendText(event);
                    log.info("[InfoEndpoint] Event sent");
                    }
                catch (IOException exception) {
                    System.out.println(exception);
                    }
            }
        }
    }
