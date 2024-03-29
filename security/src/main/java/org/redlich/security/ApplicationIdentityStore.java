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
package org.redlich.security;

import static java.util.Arrays.asList;

import java.util.HashSet;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

/**
 * An implementation of the IdentityStore interface to add user `Mike` and define the roles.
 */
@ApplicationScoped
public class ApplicationIdentityStore implements IdentityStore {

    /**
     * @param usernamePasswordCredential an instance of UsernamePasswordCredential
     * @return CredentialValidationResult
     */
    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {

        /*
         * This is for illustrative purposes only, and a real IdentityStore should include secure storage
         * and credential validation capabilities.
         * This example is a trivial one and is not meant to be used in production setup at all. Use of
         * hard-coded/in-memory stores or user databases trivially provided as unencrypted files etc is not
         * encouraged and has been used here in this manner just to demonstrate how a custom identity
         * store can be defined.
         */

        if (usernamePasswordCredential.compareTo("Mike", "password")) {
            return new CredentialValidationResult("Mike", new HashSet<>(asList("admin", "audit")));
            }

        return CredentialValidationResult.INVALID_RESULT;
        }
    }
