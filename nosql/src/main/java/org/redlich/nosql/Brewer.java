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
package org.redlich.nosql;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * <p>Brewer class.</p>
 *
 * @author mpredli01
 */
@Entity
public class Brewer {
    @Id
    @Positive(message = "The primary key must be a non-negative integer!")
    private int id;

    @Column
    @NotBlank(message = "The name of the brewer is required!")
    private String name;

    @Column
    @NotNull(message = "The city where the brewer resides is required!")
    private String city;

    @Column
    @NotNull(message = "The state where the brewer resides is required!")
    private String state;

    /**
     * <p>Constructor for Brewer.</p>
     */
    public Brewer() {
        this.id = 0;
        this.name = "{ brewer name }";
        this.city = "{ brewer city }";
        this.state = "{ brewer state }";
    }

    private Brewer(int id, String name, String city, String state) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
        }

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return a int
     */
    public int getId() {
        return id;
        }

    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getName() {
        return name;
        }

    /**
     * <p>Getter for the field <code>city</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getCity() {
        return city;
        }

    /**
     * <p>Getter for the field <code>state</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getState() {
        return state;
        }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Brewer { " +
                "id = '" + getId() + '\'' +
                ", name = '" + getName() + '\'' +
                ", city = '" + getCity() + '\'' +
                ", state = '" + getState() + '\'' +
                " }\n";
        }

    /**
     * <p>builder.</p>
     *
     * @return a {@link org.redlich.nosql.Brewer.BrewerBuilder} object
     */
    public static BrewerBuilder builder() {
        return new BrewerBuilder();
        }

    /**
     * <p>BrewerBuilder class.</p>
     */
    public static class BrewerBuilder {
        private int id;
        private String name;
        private String city;
        private String state;

        /**
         * <p>Default constructor.</p>
         */
        private BrewerBuilder() {
            }

        /**
         * <p>id.</p>
         * @param id the primary key of the brewer collection.
         * @return {@link BrewerBuilder}
         */
        public BrewerBuilder id(int id) {
            this.id = id;
            return this;
            }

        /**
         * <p>name.</p>
         * @param name the name of the brewer.
         * @return {@link BrewerBuilder}
         */
        public BrewerBuilder name(String name) {
            this.name = name;
            return this;
            }

        /**
         * <p>city.</p>
         * @param city the city in which the brewer resides.
         * @return {@link BrewerBuilder}
         */
        public BrewerBuilder city(String city) {
            this.city = city;
            return this;
            }

        /**
         * <p>state.</p>
         *
         * @param state the state in which the brewer resides.
         * @return {@link BrewerBuilder}
         */
        public BrewerBuilder state(String state) {
            this.state = state;
            return this;
            }

        /**
         * <p>build.</p>
         * @return {@link Brewer}
         */
        public Brewer build() {
            return new Brewer(id, name, city, state);
            }
        }
    }
