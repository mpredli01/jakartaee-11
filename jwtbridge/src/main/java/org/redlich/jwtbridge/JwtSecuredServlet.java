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

import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.identitystore.openid.JwtClaims;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@JwtAuthenticationMechanismDefinition(
        jwtClaimsDefinition = @JwtClaimsDefinition(callerNameClaim = "upn", callerGroupsClaim = "groups"),
        publicKeyDefinition = @PublicKeyDefinition(key = "", location = "", algorithm = "RS256"),
        decryptionKeyDefinition = @PrivateKeyDefinition(location = "", algorithm = ""),
        jwtClaimsVerification = @JwtClaimsVerification(issuer = "", audiences = "", tokenAge = 0, tokenAgeExpression = "", clockSkew = 0, clockSkewExpression = ""),
        httpHeadersDefinition = @HttpHeadersDefinition(tokenHeader = "Authorization", cookieName = "Bearer"),
        jwksDefinition = @JwksDefinition(jwksConnectTimeout = 500, jwksConnectTimeoutExpression = "", jwksReadTimeout = 500, jwksReadTimeoutExpression = ""))
public class JwtSecuredServlet extends HttpServlet {

    @Inject
    SecurityContext securityContext;

    Class ptype;

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // example of obtaining JWT claims from Jakarta SecurityContext
        JwtClaims jwtClaims = securityContext.getPrincipalsByType(ptype);
        }
    }
