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
package org.redlich.servlet;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("hello")
public class HelloWorldResource {

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Inject
    HelloWorldService service;

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public String hello(@QueryParam("name") String name) {
        HelloWorld hello = new HelloWorld();
        if((name == null) || name.trim().isEmpty())  {
            hello.setName("World");
            }
        else {
            hello.setName(name);
            }
        StringBuilder builder = new StringBuilder();
        builder.append(this.message);
        builder.append("\n\n");
        builder.append("Hello, " + hello.getName() + "!");
        builder.append("\n");
        builder.append(service.message());
        builder.append("\n");

        return builder.toString();
        }
    }
