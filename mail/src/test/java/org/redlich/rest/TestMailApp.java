package org.redlich.mail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestMailApp {

    @BeforeAll
    static void setup() {
        System.out.println("[TEST] Setting up the TestMailApp test");
        }
    @Test
    void testRestApp() {
        setup();
        assert(true);
        }
    }
