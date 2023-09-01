package org.redlich.nosql;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestBeerApp {
    @BeforeAll
    static void setup() {
        System.out.println("[TEST] Setting up the TestBeerApp test");
        }
    @Test
    void testBeerApp() {
        setup();
        assert(true);
        }
    }
