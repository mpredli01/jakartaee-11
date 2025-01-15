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
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Test Servlet that prints out the name of the authenticated caller and whether
 * this caller is in any of the roles {admin, audit, user}
 */
@WebServlet("/security")
@DeclareRoles({ "admin", "audit", "user" })
@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))
public class SingleSecurityServlet extends HttpServlet {

    SingleSecurityServlet() {
        }

    private static final long serialVersionUID = 1L;

    /**
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String webName = null;
        if (request.getUserPrincipal() != null) {
            webName = request.getUserPrincipal().getName();
            }
        response.getWriter().write("web username: " + webName + "\n");

        response.getWriter().write("web user has role \"admin\": " + request.isUserInRole("admin") + "\n");
        response.getWriter().write("web user has role \"audit\": " + request.isUserInRole("audit") + "\n");
        response.getWriter().write("web user has role \"user\": " + request.isUserInRole("user") + "\n");
        }
    }
