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

public class User {

    User(String name, boolean working, String aboutMe, int age, String email) {
        setName(name);
        setWorking(true);
        setAboutMe(aboutMe);
        setAge(age);
        setEmail(email);
        }

    @NotNull(message = "Name cannot be null")
    private String name;

    @AssertTrue
    private boolean working;

    @Size(min = 10, max = 200, message = "About Me must be between 10 and 200 characters")
    private String aboutMe;

    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;

    @Email(message = "Email should be valid")
    private String email;

    public String getName() {
        return name;
        }

    public boolean isWorking() {
        return working;
        }

    public String getAboutMe() {
        return aboutMe;
        }

    public int getAge() {
        return age;
        }

    public String getEmail() {
        return email;
        }


    public void setName(String name) {
        this.name = name;
        }

    public void setWorking(boolean working) {
        this.working = working;
        }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
        }

    public void setAge(int age) {
        this.age = age;
        }

    public void setEmail(String email) {
        this.email = email;
        }

    @Override
    public String toString() {
        return "User{" +
                "name='" + getName() + '\'' +
                ", working=" + isWorking() +
                ", aboutMe='" + getAboutMe() + '\'' +
                ", age=" + getAge() +
                ", email='" + getEmail() + '\'' +
                '}';
        }
    }