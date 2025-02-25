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
package org.redlich.data;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

/**
 * A POJO representing the Beer collection in the MongoDB database.
 *
 * @author mpredli01
 * @version $Id: $Id
 */
@Entity
public class Beer {
    /**
     *
     */
    @Id
    @Positive(message = "The primary key must be a non-negative integer!")
    private int id;
    /**
     *
     */
    @Column
    @NotBlank(message = "The name of the beer is required!")
    private String name;

    /**
     *
     */
    @Column
    @NotNull(message = "The beer type is required!")
    private BeerType type;

    /**
     *
     */
    @Column("brewer_id")
    @NotNull(message = "The brewerId is required!")
    private int brewerId;

    /**
     *
     */
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

    /**
     *
     * @param id - the id
     * @param name - the beer name
     * @param type - the beer type
     * @param brewerId - the brewerId
     * @param abv - the ABV
     */
    private Beer(int id, String name, BeerType type, int brewerId, double abv) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.brewerId = brewerId;
        this.abv = abv;
        }

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return id the primary key the Beer entity.
     */
    public int getId() {
        return id;
        }

    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return name the name of the beer.
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
     * @return brewerId the value of `brewerId` from the Brewer entity.
     */
    public int getBrewerId() {
        return brewerId;
        }

    /**
     * <p>Getter for the field <code>abv</code>.</p>
     *
     * @return abv the value of `abv`.
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

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return id == beer.id
                && brewerId == beer.brewerId
                && Double.compare(abv, beer.abv) == 0
                && Objects.equals(name, beer.name)
                && type == beer.type;
        }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, brewerId, abv);
        }

    /**
     * <p>builder.</p>
     *
     * @return BeerBuilder a new instance of the BeerBuilder.
     */
    public static BeerBuilder builder() {
        return new BeerBuilder();
        }

    /**
     * A builder pattern for the Beer class.
     */
    public static class BeerBuilder {
        private int id;
        private String name;
        private BeerType type;
        private int brewerId;
        private double abv;

        private BeerBuilder() {
            }

        /**
         * Returns the primary key in the Beer database collection.
         * @param id - the primary key
         * @return id
         */
        public BeerBuilder id(int id) {
            this.id = id;
            return this;
            }

        /**
         * Returns the name of the beer.
         * @param name - the name of the beer.
         * @return - the name of the beer.
         */
        public BeerBuilder name(String name) {
            this.name = name;
            return this;
            }

        /**
         * Returns the beer typ, i.e., ale, stout, etc.
         * @param type - beer type
         * @return BeerBuilder
         */
        public BeerBuilder type(BeerType type) {
            this.type = type;
            return this;
            }

        /**
         *
         * @param brewerId - the brewerId
         * @return BeerBuilder
         */
        public BeerBuilder brewerId(int brewerId) {
            this.brewerId = brewerId;
            return this;
            }

        /**
         * Returns the alcohol by volume.
         * @param abv - the alcohol by volume value
         * @return BeerBuilder
         */
        public BeerBuilder abv(double abv) {
            this.abv = abv;
            return this;
            }

        /**
         * Returns an instance of the Beer class.
         * @return Beer
         */
        public Beer build() {
            return new Beer(id, name, type, brewerId, abv);
            }
        }
    }
