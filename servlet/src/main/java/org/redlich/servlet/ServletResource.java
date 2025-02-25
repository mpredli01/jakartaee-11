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

/**
 * <p>ServletResource class.</p>
 *
 * @author mpredli01
 */
@Path("hello")
public class ServletResource {

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Inject
    ServletService servletService;

    /**
     * <p>hello.</p>
     *
     * @param name a {@link java.lang.String} object
     * @return a {@link java.lang.String} object
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public String hello(@QueryParam("name") String name) {
        StringBuilder builder = new StringBuilder();
        builder.append(this.message);
        builder.append("\n\n");
        // builder.append("Hello, " + hello.getName() + "!");
        if((name == null) || name.trim().isEmpty()) {
            builder.append("Hello, World!");
            }
        else {
            builder.append("Hello, " + name + "!");
        }
        builder.append("\n\n");
        builder.append(servletService.message());
        return builder.toString();
        }
    }
