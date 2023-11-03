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
package org.redlich.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/storePreferences")
public class StorePreferencesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/coffee_preferences.html").forward(req, resp);
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] coffeeTypes = req.getParameterValues("coffeeType");
        HttpSession session = req.getSession();
        session.setAttribute("userCoffeeTypes", coffeeTypes);

        resp.sendRedirect("coffeeDashboard");
        }
    }
