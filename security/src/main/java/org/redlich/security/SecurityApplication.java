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
package org.redlich.security;

import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Define a container authentication mechanism that implements the HTTP basic access authentication protocol as defined by the Servlet spec (13.6.1) and make that implementation available as an enabled CDI bean.
 *
 * @author mpredli01
 */

@ApplicationPath("/security")
@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class SecurityApplication extends Application {

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Inject
    SecurityService securityService;

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
        builder.append(securityService.message());
        return builder.toString();
        }
    }
