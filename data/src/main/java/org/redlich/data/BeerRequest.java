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

public record BeerRequest(String name, BeerType type, int brewerId, double abv) {

    public Beer createBeer(int id) {
        return Beer.builder()
                .id(id)
                .name(name)
                .type(type)
                .brewerId(brewerId)
                .abv(abv)
                .build();
        }
    }
