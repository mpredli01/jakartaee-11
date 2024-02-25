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

/**
 * A command-line version of the application should it actually be implemented.
 */
public class SecurityApp {

    /**
     * Constructor
     */
    SecurityApp() {
        }

    /**
     * The entry point for the command-line application
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SecurityApp app = new SecurityApp();
        String title = "[APP] Welcome to the Jakarta Security Demo Application";
        app.displayTitle(title);
        }

    /**
     * Displays the title of the application surrounded by a dotted box.
     *
     * @param title the specified title for the application
     */
    public void displayTitle(String title) {
        int length = title.length();
        System.out.print("[APP] ");
        for(int i = 0; i < length; ++i) {
            System.out.print("-");
            }
        System.out.println();
        System.out.println(title);
        System.out.print("[APP] ");
        for(int i = 0; i < length; ++i) {
            System.out.print("-");
            }
        System.out.println();
        }
    }
