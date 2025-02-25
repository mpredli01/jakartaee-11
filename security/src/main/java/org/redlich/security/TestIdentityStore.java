package org.redlich.security;

import static java.util.Arrays.asList;
import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

import java.util.HashSet;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

/**
 * <p>TestIdentityStore class.</p>
 *
 * @author mpredli01
 */
@ApplicationScoped
public class TestIdentityStore implements IdentityStore {

    /**
     * <p>validate.</p>
     *
     * @param usernamePasswordCredential a {@link jakarta.security.enterprise.credential.UsernamePasswordCredential} object
     * @return a {@link jakarta.security.enterprise.identitystore.CredentialValidationResult} object
     */
    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {

        if (usernamePasswordCredential.compareTo("reza", "secret1")) {
            return new CredentialValidationResult("reza", new HashSet<>(asList("foo", "bar")));
            }

        return INVALID_RESULT;
        }
    }
