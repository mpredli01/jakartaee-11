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

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Set;

import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * <p>ValidationResource class.</p>
 *
 * @author mpredli01
 */
@Path("validate")
public class ValidationResource {

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Inject
    ValidationService validationService;

    /**
     * <p>validate.</p>
     *
     * @return a {@link java.lang.String} object
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String validate() {
        User user = new User(null, true, "Java Champion", 13, "mike");
        UserRecord userRecord = new UserRecord(null, true, "Java Champion", 13, "mike");

        StringBuilder builder = new StringBuilder();
        builder.append(this.message);
        builder.append("\n\n");
        builder.append(userRecord);
        builder.append("\n");

        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<UserRecord>> violations = validator.validate(userRecord);
            for (ConstraintViolation<UserRecord> violation : violations) {
                builder.append(violation.getMessage());
                builder.append("\n");
                }
            builder.append("\n");
            builder.append(validationService.message());
            builder.append("\n");
            }
        return builder.toString();
        }
    }
