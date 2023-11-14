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

import com.github.javafaker.Faker;

import java.util.Objects;
import java.util.UUID;

@Entity
public class FakerBeer {

    @Id
    String id;

    @Column
    String name;

    @Column
    String hop;
    
    @Column
    String malt;

    @Column
    String style;

    @Column
    String yeast;

    public String getId() {
        return id;
        }

    public void setId(String id) {
        this.id = id;
        }

    public String getName() {
        return name;
        }

    public void setName(String name) {
        this.name = name;
        }

    public String getHop() {
        return hop;
        }

    public void setHop(String hop) {
        this.hop = hop;
        }

    public String getMalt() {
        return malt;
        }

    public void setMalt(String malt) {
        this.malt = malt;
        }

    public String getStyle() {
        return style;
        }

    public void setStyle(String style) {
        this.style = style;
        }

    public String getYeast() {
        return yeast;
        }

    public void setYeast(String yeast) {
        this.yeast = yeast;
        }

    @Override
    public String toString() {
        return "FakerBeer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", hop='" + hop + '\'' +
                ", malt='" + malt + '\'' +
                ", style='" + style + '\'' +
                ", yeast='" + yeast + '\'' +
                '}';
        }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        FakerBeer fakerBeer = (FakerBeer) object;
        return Objects.equals(id, fakerBeer.id);
        }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
        }

    public static FakerBeer of(Faker faker) {
        var beer = faker.beer();
        FakerBeer fakerBeer1 = new FakerBeer();
        fakerBeer1.id = UUID.randomUUID().toString();
        fakerBeer1.name = beer.name();
        fakerBeer1.hop = beer.hop();
        fakerBeer1.malt = beer.malt();
        fakerBeer1.style = beer.style();
        fakerBeer1.yeast = beer.yeast();
        return fakerBeer1;
        }
    }