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

/**
 * <p>User class.</p>
 *
 * @author mpredli01
 */
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

    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getName() {
        return name;
        }

    /**
     * <p>isWorking.</p>
     *
     * @return a boolean
     */
    public boolean isWorking() {
        return working;
        }

    /**
     * <p>Getter for the field <code>aboutMe</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getAboutMe() {
        return aboutMe;
        }

    /**
     * <p>Getter for the field <code>age</code>.</p>
     *
     * @return a int
     */
    public int getAge() {
        return age;
        }

    /**
     * <p>Getter for the field <code>email</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getEmail() {
        return email;
        }


    /**
     * <p>Setter for the field <code>name</code>.</p>
     *
     * @param name a {@link java.lang.String} object
     */
    public void setName(String name) {
        this.name = name;
        }

    /**
     * <p>Setter for the field <code>working</code>.</p>
     *
     * @param working a boolean
     */
    public void setWorking(boolean working) {
        this.working = working;
        }

    /**
     * <p>Setter for the field <code>aboutMe</code>.</p>
     *
     * @param aboutMe a {@link java.lang.String} object
     */
    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
        }

    /**
     * <p>Setter for the field <code>age</code>.</p>
     *
     * @param age a int
     */
    public void setAge(int age) {
        this.age = age;
        }

    /**
     * <p>Setter for the field <code>email</code>.</p>
     *
     * @param email a {@link java.lang.String} object
     */
    public void setEmail(String email) {
        this.email = email;
        }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "User{ " +
                "name='" + getName() + '\'' +
                ", working=" + isWorking() +
                ", aboutMe='" + getAboutMe() + '\'' +
                ", age=" + getAge() +
                ", email='" + getEmail() + '\'' +
                " }";
        }
    }
