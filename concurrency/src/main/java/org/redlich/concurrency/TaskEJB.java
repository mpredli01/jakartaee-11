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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.enterprise.concurrent.ManagedScheduledExecutorService;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

/**
 * <p>TaskEJB class.</p>
 *
 * @author mpredli01
 */
@Startup
@Singleton
@LocalBean
@Path("/taskinfo")
public class TaskEJB {

    private static final Logger log = Logger.getLogger("TaskEJB");

    /* Inject the default managed executor from the app server */
    @Resource(name="java:comp/DefaultManagedExecutorService")
    ManagedExecutorService mExecService;
    /* Inject the default managed scheduled executor from the app server */
    @Resource(name="java:comp/DefaultManagedScheduledExecutorService")
    ManagedScheduledExecutorService sExecService;

    /* Keep track of periodic tasks so we can kill them later */
    private Map<String, ScheduledFuture<?>> periodicTasks;
    /* Keep the log (textarea content) for all clients in this EJB */
    private String infoField;
    /* Fire CDI events for the WebSocket endpoint */
    @Inject
    private Event<String> events;

    /**
     * <p>init.</p>
     */
    @PostConstruct
    public void init() {
        periodicTasks = new HashMap<>();
        infoField = "";
        }

    /**
     * <p>destroy.</p>
     */
    @PreDestroy
    public void destroy() {
        /* Cancel periodic tasks */
        log.info("[TaskEJB] Cancelling periodic tasks");
        for (ScheduledFuture<?> fut : periodicTasks.values())
            fut.cancel(true);
        mExecService.shutdownNow();
        sExecService.shutdownNow();
        }

    /**
     * <p>submitTask.</p>
     *
     * @param task a {@link org.redlich.concurrency.Task} object
     * @param type a {@link java.lang.String} object
     */
    public void submitTask(Task task, String type) {
        /* Use the managed executor objects from the app server
         * to schedule the tasks */
        switch (type) {
            case "IMMEDIATE":
                mExecService.submit(task);
                break;
            case "DELAYED":
                sExecService.schedule(task, 3, TimeUnit.SECONDS);
                break;
            case "PERIODIC":
                ScheduledFuture<?> fut;
                fut = sExecService.scheduleAtFixedRate(task, 0, 8,
                        TimeUnit.SECONDS);
                periodicTasks.put(task.getName(), fut);
                break;
            }
        }

    /**
     * <p>cancelPeriodicTask.</p>
     *
     * @param name a {@link java.lang.String} object
     */
    public void cancelPeriodicTask(String name) {
        /* Cancel a periodic task */
        if (periodicTasks.containsKey(name)) {
            log.log(Level.INFO, "[TaskEJB] Cancelling task {0}", name);
            periodicTasks.get(name).cancel(true);
            periodicTasks.remove(name);
            /* Notify the WebSocket endpoint to update the client's task list */
            events.fire("tasklist");
            }
        }

    /**
     * <p>addToInfoField.</p>
     *
     * @param msg a {@link java.lang.String} object
     */
    @POST
    @Consumes("text/html")
    /* The tasks post updates to this JAX-RS endpoint */
    public void addToInfoField(String msg) {
        /* Update the log */
        infoField = msg + "\n" + infoField;
        log.log(Level.INFO, "[TaskEJB] Added message {0}", msg);
        /* Notify the WebSocket endpoint to update the client's task log */
        events.fire("infobox");
        }

    /* Provide the execution log for the client's pages */
    /**
     * <p>Getter for the field <code>infoField</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getInfoField() {
        return infoField;
        }

    /**
     * <p>clearInfoField.</p>
     */
    public void clearInfoField() {
        infoField = "";
        }

    /* Provide the list of running tasks */
    /**
     * <p>Getter for the field <code>periodicTasks</code>.</p>
     *
     * @return a {@link java.util.Set} object
     */
    public Set<String> getPeriodicTasks() {
        return periodicTasks.keySet();
        }
    }
