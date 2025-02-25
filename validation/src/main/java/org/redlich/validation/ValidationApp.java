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
package org.redlich.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

/**
 * <p>ValidationApp class.</p>
 *
 * @author mpredli01
 */
public class ValidationApp {

    ValidationApp() {
        }

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects
     */
    public static void main(String[] args) {
        ValidationApp app = new ValidationApp();
        String title = "[APP] Welcome to the Jakarta Validation Demo Application";
        app.displayTitle(title);

        User user = new User(null, true, "Java Champion", 13, "mike");
        System.out.println("[APP] The user is: " + user);
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        System.out.println("[APP] Checking validation rules:");
        for(ConstraintViolation<User> violation : violations) {
            System.out.println("[APP] * " + violation.getMessage());
            }
        }

    /**
     * <p>displayTitle.</p>
     *
     * @param title a {@link java.lang.String} object
     */
    public void displayTitle(String title) {
        int length = title.length();
        System.out.print("[APP] ");
        for(int i = 0; i < length; ++i) {
            System.out.print("-");
            }
        System.out.println();
        System.out.println(title);
        System.out.print("[APP] ");
        for(int i = 0; i < length; ++i) {
            System.out.print("-");
            }
        System.out.println();
        }
    }
