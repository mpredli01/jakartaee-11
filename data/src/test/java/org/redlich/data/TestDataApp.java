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
package org.redlich.data;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestDataApp {

    @BeforeAll
    static void setup() {
        System.out.println("[TEST] Setting up the TestDataApp test");
        }
    @Test
    void testDataApp() {
        setup();
        assert(true);
        }
    }
