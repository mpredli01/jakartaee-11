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
