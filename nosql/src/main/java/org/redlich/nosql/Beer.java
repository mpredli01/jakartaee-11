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
import jakarta.nosql.Id;
import jakarta.nosql.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

/**
 * <p>Beer class.</p>
 *
 * @author mpredli01
 */
@Entity
public class Beer {
    @Id
    @Positive(message = "The primary key must be a non-negative integer!")
    private int id;

    @Column
    @NotBlank(message = "The name of the beer is required!")
    private String name;

    @Column
    @NotNull(message = "The beer type is required!")
    private BeerType type;

    @Column("brewer_id")
    @NotNull(message = "The brewerId is required!")
    private int brewerId;

    @Column
    @NotNull(message = "The alcohol by volume (ABV) is required!")
    private double abv;

    /**
     * <p>Constructor for Beer.</p>
     */
    public Beer() {
        id = 0;
        name = "{ beer name }";
        type = BeerType.ALE;
        brewerId = 0;
        abv = 10.0;
        }

    private Beer(int id, String name, BeerType type, int brewerId, double abv) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.brewerId = brewerId;
        this.abv = abv;
        }

    /**
     * public int getId()
     *
     * @return id of the Beer entity.
     */
    public int getId() {
        return id;
        }

    /**
     * public String getName()
     *
     * @return the name of the beer.
     */
    public String getName() {
        return name;
        }

    /**
     * <p>Getter for the field <code>type</code>.</p>
     *
     * @return the beer type.
     */
    public BeerType getType() {
        return type;
        }

    /**
     * <p>Getter for the field <code>brewerId</code>.</p>
     *
     * @return the value of `brewerId` from the Brewer entity.
     */
    public int getBrewerId() {
        return brewerId;
        }

    /**
     * <p>Getter for the field <code>abv</code>.</p>
     *
     * @return the value of `abv`.
     */
    public double getAbv() {
        return abv;
        }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Beer { " +
                "id = '" + getId() + '\'' +
                ", name = '" + getName() + '\'' +
                ", type = '" + getType() + '\'' +
                ", brewer_id = '" + getBrewerId() + '\'' +
                ", abv = '" + getAbv() + '\'' +
                " }\n";
        }

    /**
     * <p>builder.</p>
     *
     * @return a {@link org.redlich.nosql.Beer.BeerBuilder} object
     */
    public static BeerBuilder builder() {
        return new BeerBuilder();
        }

    public static class BeerBuilder {
        private int id;
        private String name;
        private BeerType type;
        private int brewer_id;
        private double abv;

        private BeerBuilder() {
            }

        public BeerBuilder id(int id) {
            this.id = id;
            return this;
            }

        public BeerBuilder name(String name) {
            this.name = name;
            return this;
            }

        public BeerBuilder type(BeerType type) {
            this.type = type;
            return this;
            }

        public BeerBuilder brewer_id(int brewer_id) {
            this.brewer_id = brewer_id;
            return this;
            }

        public BeerBuilder abv(double abv) {
            this.abv = abv;
            return this;
            }

        public Beer build() {
            return new Beer(id, name, type, brewer_id, abv);
        }
        }
    }
