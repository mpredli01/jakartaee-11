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
package org.redlich.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Application;

import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;


/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 * @author airhacks.com
 */
@ApplicationScoped
@ApplicationPath("/rest")
@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class RestApplication extends Application {

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Inject
    RestService restService;

    /**
     * <p>Default constructor.</p>
     */
    public RestApplication() {
        }

    /**
     * <p>sayHello.</p>
     *
     * @return a {@link java.lang.String} object
     */
    @GET
    public String sayHello() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.message);
        builder.append("\n\n");
        builder.append(restService.message());
        return builder.toString();
        }
    }
