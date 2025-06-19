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

import java.io.Serializable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

/**
 *Bean for the index.xhtml JSF page
 *
 * @author mpredli01
 */
@Named
@SessionScoped
public class TaskCreatorBean implements Serializable {

    private static final long serialVersionUID = 7393500655950989708L;
    private static final Logger log = Logger.getLogger("TaskCreatorBean");

    /**
     * <p>taskEJB.</p>
     */
    @EJB
    TaskEJB taskEJB;

    /**
     * <p>taskMessages.</p>
     */
    private String taskMessages;

    /**
     * <p>taskType.</p>
     */
    private String taskType;

    /**
     * <p>taskName.</p>
     */
    private String taskName;

    /**
     * <p>periodicTask.</p>
     */
    private String periodicTask;

    /**
     * <p>Constructor for TaskCreatorBean.</p>
     */
    public TaskCreatorBean() {
        taskMessages = "";
        taskType = "IMMEDIATE";
        taskName = "";
        periodicTask = "";
        }

    /**
     * <p>Setter for the field <code>taskMessages</code>.</p>
     *
     * @param msg a {@link java.lang.String} object
     */
    public void setTaskMessages(String msg) {
        taskMessages = msg;
        }
    /**
     * <p>Getter for the field <code>taskMessages</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getTaskMessages() {
        taskMessages = taskEJB.getInfoField();
        return taskMessages;
        }

    /**
     * <p>Setter for the field <code>taskType</code>.</p>
     *
     * @param t a {@link java.lang.String} object
     */
    public void setTaskType(String t) {
        taskType = t;
        }
    /**
     * <p>Getter for the field <code>taskType</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getTaskType() {
        return taskType;
        }

    /**
     * <p>Setter for the field <code>taskName</code>.</p>
     *
     * @param n a {@link java.lang.String} object
     */
    public void setTaskName(String n) {
        taskName = n;
        }
    /**
     * <p>Getter for the field <code>taskName</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getTaskName() {
        return taskName;
        }

    /**
     * <p>Setter for the field <code>periodicTask</code>.</p>
     *
     * @param t a {@link java.lang.String} object
     */
    public void setPeriodicTask(String t) {
        periodicTask = t;
        }
    /**
     * <p>Getter for the field <code>periodicTask</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getPeriodicTask() {
        return periodicTask;
        }

    /**
     * <p>getPeriodicTasks.</p>
     *
     * @return a {@link java.util.Set} object
     */
    public Set<String> getPeriodicTasks() {
        return taskEJB.getPeriodicTasks();
        }

    /* Executed via an AJAX call in the Submit button */
    /**
     * <p>submitTask.</p>
     */
    public void submitTask() {
        if (taskEJB.getPeriodicTasks().contains(taskName) == false) {
            /* Create a new task object */
            Task task = new Task(taskName, taskType);
            /* Use the managed executor objects to run the task */
            taskEJB.submitTask(task, taskType);
            taskType = "IMMEDIATE";
            taskName = "";
            }
        }

    /* Executed via an AJAX call in the Cancel Task button */
    /**
     * <p>cancelTask.</p>
     */
    public void cancelTask() {
        log.log(Level.INFO, "[TaskCreatorBean] Cancelling task {0}", periodicTask);
        taskEJB.cancelPeriodicTask(periodicTask);
        }

    /* Executed via an AJAX call in the Clean Log button */
    /**
     * <p>clearInfoField.</p>
     */
    public void clearInfoField() {
        taskEJB.clearInfoField();
        }
    }
