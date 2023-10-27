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

import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.identitystore.openid.JwtClaims;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.redlich.jwtbridge.authentication.HttpHeadersDefinition;
import org.redlich.jwtbridge.authentication.JwtAuthenticationMechanismDefinition;
import org.redlich.jwtbridge.authentication.JwtClaimsDefinition;
import org.redlich.jwtbridge.authentication.JwtClaimsVerification;
import org.redlich.jwtbridge.authentication.JwksDefinition;
import org.redlich.jwtbridge.authentication.PrivateKeyDefinition;
import org.redlich.jwtbridge.authentication.PublicKeyDefinition;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Set;

@WebServlet("/jwtbridge")
@DeclareRoles({ "foo", "bar", "kaz" })
@ServletSecurity(@HttpConstraint(rolesAllowed = "foo"))
@JwtAuthenticationMechanismDefinition(
        jwtClaimsDefinition = @JwtClaimsDefinition(callerNameClaim = "upn", callerGroupsClaim = "groups"),
        publicKeyDefinition = @PublicKeyDefinition(key = "key", location = "location", algorithm = "RS256"),
        decryptionKeyDefinition = @PrivateKeyDefinition(location = "location", algorithm = "RS256"),
        jwtClaimsVerification = @JwtClaimsVerification(issuer = "issuer", audiences = "aud", tokenAge = 0, tokenAgeExpression = "", clockSkew = 0, clockSkewExpression = ""),
        httpHeadersDefinition = @HttpHeadersDefinition(tokenHeader = "Authorization", cookieName = "Bearer"),
        jwksDefinition = @JwksDefinition(jwksConnectTimeout = 500, jwksConnectTimeoutExpression = "", jwksReadTimeout = 500, jwksReadTimeoutExpression = ""))
public class JwtSecuredServlet extends HttpServlet {

    @Inject
    SecurityContext securityContext;

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // example of obtaining JWT claims from Jakarta SecurityContext
        // JwtClaims jwtClaims = securityContext.getPrincipalsByType(ptype);
        // Set<JwtClaims> jwtClaims = securityContext.getPrincipalsByType(JwtClaims.class);

        // Example 1: Is the caller is one of the three roles: admin, user and demo
        PrintWriter pw = response.getWriter();

        pw.write(message);

        boolean role = securityContext.isCallerInRole("admin");
        pw.write("User has role 'admin': " + role + "\n");

        role = securityContext.isCallerInRole("user");
        pw.write("User has role 'user': " + role + "\n");

        role = securityContext.isCallerInRole("demo");
        pw.write("User has role 'demo': " + role + "\n");

        // Example 2: What is the caller principal name
        String contextName = null;
        if (securityContext.getCallerPrincipal() != null) {
            contextName = securityContext.getCallerPrincipal().getName();
            }
        response.getWriter().write("context username: " + contextName + "\n");

        Set<Principal> principals = securityContext.getPrincipalsByType(Principal.class);
        for(Principal customPrincipal : principals) {
            response.getWriter().write((customPrincipal.getName()));
            }
        }
    }
