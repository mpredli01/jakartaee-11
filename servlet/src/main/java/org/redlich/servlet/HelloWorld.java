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

public class HelloWorld {

    private String name;

    public HelloWorld(String name) {
        this.name = name;
        }

    public HelloWorld() {
        this.name = "world";
    }

    public String getName() {
        return name;
        }

    public void setName(String name) {
        this.name = name;
        }
    }
