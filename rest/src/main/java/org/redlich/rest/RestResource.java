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

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("rest")
public class RestResource {

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Inject
    RestService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String rest() {

        StringBuilder builder = new StringBuilder();
        builder.append(this.message);
        builder.append("\n");
        builder.append(service.message());
        builder.append("\n");
        return builder.toString();
        }
    }
