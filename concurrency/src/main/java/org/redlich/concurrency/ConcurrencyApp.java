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
package org.redlich.concurrency;

public class ConcurrencyApp {

    ConcurrencyApp() {
        }

    public static void main(String[] args) {
        ConcurrencyApp app = new ConcurrencyApp();
        String title = "[APP] Welcome to the Jakarta Concurrency Demo Application";
        app.displayTitle(title);
        String payara = "[APP] Hosted on the Payara Platform and serverless Payara Cloud";
        app.displayTitle(payara);
        
        System.out.println("[APP] Brought to you by Payara | Garden State JUG | Jakarta EE");
        System.out.println("[APP] This is the Task Creator example application");
        System.out.println();
        System.out.println("[APP] Instructions:");
        System.out.println("[APP] * Execute the command:");
        System.out.println("[APP]      $mvn clean package && ./start-payara-micro.sh");
        System.out.println("[APP] * In your browser, use the URL:");
        System.out.println("[APP]      http://localhost:8080/concurrency-1.0.0");
        }

    /**
     *
     * @param title the title to display.
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
