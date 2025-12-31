package org.redlich.security;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public final class Assert {

    public static void assertDefaultNoAccess(String response) {
        assertFalse(
                "Should not have access to servlet, but had access.\n" +
                        response,
                response.contains("This is a servlet"));
    }

    public static void assertDefaultAccess(String response) {
        assertTrue(
                "Should have access to servlet, but had no access.\n" +
                        response,
                response.contains("This is a servlet"));
    }

    public static void assertDefaultAuthenticated(String response) {
        assertAuthenticated("web", "reza", response, "foo", "bar");
    }

    public static void assertDefaultNotAuthenticated(String response) {
        assertNotAuthenticated("web", "reza", response, "foo", "bar");
    }

    public static void assertAuthenticated(String userType, String name, String response, String... roles) {
        assertTrue(
                "Should be authenticated as user " + name + " but was not \n Response: \n" +
                        response + "\n search: " + userType + " username: " + name,
                response.contains(userType + " username: " + name));

        for (String role : roles) {
            assertTrue(
                    "Authenticated user should have role \"" + role + "\", but did not \n Response: \n" +
                            response,
                    response.contains(userType + " user has role \"" + role + "\": true"));
        }
    }

    public static void assertNotAuthenticated(String userType, String name, String response, String... roles) {
        assertFalse(
                "Should not be authenticated as user " + name + " but was \n Response: \n" +
                        response + "\n search: " + userType + " username: " + name,
                response.contains(userType + " username: " + name));

        for (String role : roles) {
            assertFalse(
                    "Authenticated user should not have role \"" + role + "\", but did \n Response: \n" +
                            response,
                    response.contains(userType + " user has role \"" + role + "\": true"));
        }
    }

}
