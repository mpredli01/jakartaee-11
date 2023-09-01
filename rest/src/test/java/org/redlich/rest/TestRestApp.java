package org.redlich.rest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestRestApp {

    @BeforeAll
    static void setup() {
        System.out.println("[TEST] Setting up the TestRestApp test");
        }
    @Test
    void testRestApp() {
        setup();
        assert(true);
        }
    }
