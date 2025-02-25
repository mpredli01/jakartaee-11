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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>CoffeeDashboardServlet class.</p>
 *
 * @author mpredli01
 */
@WebServlet("/coffeeDashboard")
public class CoffeeDashboardServlet extends HttpServlet {

    //This could ideally  come from a database
    private static final Map<String, String> COFFEE_DESCRIPTIONS = new HashMap<>();

    static {
        COFFEE_DESCRIPTIONS.put("Black", """
           <p>Black coffee has a robust flavor, perfect for those who prefer a coffee with some bite.</p>
           <p>Try brewing methods like French Press or Aeropress for an enjoyable black coffee experience.</p>
       """);
        COFFEE_DESCRIPTIONS.put("Latte", """
           <p>A latte is a creamy delight, suitable for people who enjoy a smoother and less harsh flavor.</p>
           <p>Experimenting with various syrups and sweeteners can elevate your latte experience.</p>
       """);
        COFFEE_DESCRIPTIONS.put("Cold Brew", """
           <p>Cold brew coffee tends to be smoother and less acidic. It's perfect for those hot summer days.</p>
           <p>Try brewing a batch in the fridge overnight for a refreshing morning pick-me-up.</p>
       """);
    }

    /** {@inheritDoc} */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String[] coffeeTypes = (String[]) session.getAttribute("userCoffeeTypes");

        if (coffeeTypes == null || coffeeTypes.length == 0) {
            handleNoCoffeeTypes(resp);
            return;
            }

        PrintWriter out = resp.getWriter();
        out.println("""
           <html>
           <head>
               <title>Personalized Coffee Dashboard</title>
               <style>
                   li, a, p, h2, h3, p, td, h1 { font-family: Arial; color: white;}
                   body { background-color: slategray;}
               </style>
           </head>
              
           <body>
           <h1>Your Personalized Coffee Dashboard</h1>
       """);

        for (String coffeeType : coffeeTypes) {
            String additionalInfo = COFFEE_DESCRIPTIONS.get(coffeeType);
            out.println("""
           <h2>Recommended %s</h2>
           <p>Here are some %s blends you might enjoy.</p>
           %s
       """.formatted(coffeeType, coffeeType, additionalInfo));
            }

        out.println("""
           </body>
       </html>
       """);
        }

    private void handleNoCoffeeTypes(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("""
       <html>
       <head>
           <title>No Coffee Types Found</title>
           <style>
               li, a, p, h2, h3, p, td, h1 { font-family: Arial; color: white;}
               body { background-color: slategray;}
           </style>
       </head>

       <body>
           <h1>No Coffee Types Found</h1>
           <p>Please select at least one type of coffee.</p>
       </body>
       </html>
       """);
        }
    }
