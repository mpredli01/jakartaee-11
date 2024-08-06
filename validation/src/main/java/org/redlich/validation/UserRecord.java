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

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public record UserRecord(@NotNull String name,
                         @AssertTrue boolean working,
                         @Size(min = 10, max = 200, message = "About Me must be between 10 and 200 characters") String aboutMe,
                         @Min(value = 18, message = "Age should not be less than 18") @Max(value = 150, message = "Age should not be greater than 150") int age,
                         @Email(message = "Email should be valid") String email) {
    @Override
    public String toString() {
        return "UserRecord{ " +
                "name='" + name() + '\'' +
                ", working=" + working +
                ", aboutMe='" + aboutMe() + '\'' +
                ", age=" + age() +
                ", email='" + email() + '\'' +
                " }";
    }

}
