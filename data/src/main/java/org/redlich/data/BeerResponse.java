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

/**
 * <p>BeerResponse class.</p>
 *
 * @param id the primary key.
 * @param name the name of the beer.
 * @param type the beer type.
 * @param brewerId the brewerId from the {@link org.redlich.data.Brewer} object.
 * @param abv the ABV of the beer.
 * @author mpredli01
 */
public record BeerResponse(int id, String name, BeerType type, int brewerId, double abv) {

    /**
     * <p>of.</p>
     *
     * @param beer a {@link org.redlich.data.Beer} object
     * @return a {@link org.redlich.data.BeerResponse} object
     */
    static BeerResponse of(Beer beer) {
        return new BeerResponse(beer.getId(), beer.getName(), beer.getType(), beer.getBrewerId(), beer.getAbv());
        }
    }
    
