package org.redlich.validation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestValidationApp {

    @BeforeAll
    static void setup() {
        System.out.println("[TEST] Setting up the TestValidationApp test");
        }

    @Test
    void testValidationAppTest() {
        setup();
        assert(true);
        }
    }
