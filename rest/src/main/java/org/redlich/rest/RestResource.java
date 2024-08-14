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
import jakarta.inject.Named;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Path("rest")
public class RestResource {

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Inject
    RestService service;

    @Inject
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String rest() {

        StringBuilder builder = new StringBuilder();
        builder.append(uriInfo.getMatchedResourceTemplate());
        builder.append(this.message);
        builder.append("\n");
        builder.append(service.message());
        builder.append("\n");
        return builder.toString();
        }
    }
