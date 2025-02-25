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
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * <p>RestResource class.</p>
 *
 * @author mpredli01
 */
@ApplicationScoped
@Path("rest")
public class RestResource {

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Inject
    RestService restService;

    @Context
    UriInfo uriInfo;

    /**
     * <p>rest.</p>
     *
     * @return a {@link java.lang.String} object
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String rest() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.message);
        builder.append("\n\n");
        builder.append("Matched resource template: " + uriInfo.getMatchedResourceTemplate());
        builder.append("\n\n");
        builder.append(restService.message());
        return builder.toString();
        }
    }
