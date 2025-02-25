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
package org.redlich.nosql;

import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * <p>BeerApplication class.</p>
 *
 * @author mpredli01
 * @version $Id: $Id
 */
@ApplicationPath("/nosql")
@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class NoSQLApplication extends Application {

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Inject
    NoSQLService noSQLService;

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
        builder.append(noSQLService.message());
        return builder.toString();
        }
    }
