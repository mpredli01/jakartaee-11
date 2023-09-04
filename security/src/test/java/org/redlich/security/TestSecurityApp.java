package org.redlich.security;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestSecurityApp {

    @BeforeAll
    static void setup() {
        System.out.println("[TEST] Setting up the TestSecurityApp test");
        }
    @Test
    void testSecurityApp() {
        setup();
        assert(true);
        }
    }
