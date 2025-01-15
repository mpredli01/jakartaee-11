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

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;

/**
 * Define a container authentication mechanism that implements the HTTP basic access authentication protocol as defined by the Servlet spec (13.6.1) and make that implementation available as an enabled CDI bean.
 */
@BasicAuthenticationMechanismDefinition(realmName = "file")
@ApplicationScoped
@Named
public class SecurityApplication {
    SecurityApplication() {
        }
    }
