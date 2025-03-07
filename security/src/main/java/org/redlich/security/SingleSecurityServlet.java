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

import java.io.IOException;

import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Test Servlet that prints out the name of the authenticated caller and whether
 * this caller is in any of the roles {admin, audit, user}
 *
 * @author mpredli01
 */
@WebServlet("/singleSecurityServlet")
@DeclareRoles({ "admin", "audit", "user" })
@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))
public class SingleSecurityServlet extends HttpServlet {

    /**
     * Inject the configuration property, message, from the microprofile-config.properties file.
     */
    @Inject
    @ConfigProperty(name = "message")
    String message;

    /**
     * Inject an instance of {@link SecurityService} class.
     */
    @Inject
    SecurityService securityService;

    SingleSecurityServlet() {
        }

    private static final long serialVersionUID = 1L;

    /** {@inheritDoc} */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String webName = null;
        if (request.getUserPrincipal() != null) {
            webName = request.getUserPrincipal().getName();
            }
        response.getWriter().write(message + "\n\n");
        response.getWriter().write("Web username: " + webName + "\n");
        response.getWriter().write("* user has role \"admin\": " + request.isUserInRole("admin") + "\n");
        response.getWriter().write("* user has role \"audit\": " + request.isUserInRole("audit") + "\n");
        response.getWriter().write("* user has role \"user\": " + request.isUserInRole("user") + "\n\n");
        response.getWriter().write(securityService.message());
        }
    }
