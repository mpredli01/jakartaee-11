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
package org.redlich.jwtbridge;

import org.eclipse.microprofile.jwt.config.Names;

/**
 * Annotation used to define a container AuthenticationMechanism that implements the MP-JWT authentication protocol as defined
 * by the Microprofile JWT RBAC spec and makes that implementation available as an enabled CDI bean.
 */
public @interface JwtAuthenticationMechanismDefinition {
    String verifierKey() default  "#{MPConfig.config["+ Names.VERIFIER_PUBLIC_KEY +"]}";
    String acceptedIssuer() default "#{MPConfig.config["+ Names.ISSUER +"]}";
    int clockSkew() default 30;
    }