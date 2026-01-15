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

/**
 * <p>RestService class.</p>
 *
 * @author mpredli01
 */
@ApplicationScoped
public class RestService {

    /**
     * <p>Default constructor.</p>
     */
    public RestService() {
        }

    /**
     * <p>message.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String message() {
        return "Hosted on the Payara Platform and serverless Payara Cloud!";
        }
    }
