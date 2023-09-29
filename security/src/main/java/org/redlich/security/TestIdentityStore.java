package org.redlich.security;

import static java.util.Arrays.asList;

import java.util.HashSet;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

@ApplicationScoped
public class TestIdentityStore implements IdentityStore {

    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {

        /*
         * This is for illustrative purposes only, and a real IdentityStore should include secure storage
         * and credential validation capabilities.
         * This example is a trivial one and is not meant to be used in production setup at all. Use of
         * hard-coded/in-memory stores or user databases trivially provided as unencrypted files etc is not
         * encouraged and has been used here in this manner just to demonstrate how a custom identity
         * store can be defined.
         */

        if (usernamePasswordCredential.compareTo("Joe", "secret1")) {
            return new CredentialValidationResult("Joe", new HashSet<>(asList("foo", "bar")));
            }

        return CredentialValidationResult.INVALID_RESULT;
        }
    }
