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
 * <p>BrewerRequest class.</p>
 *
 * @author mpredli01
 */
public record BrewerRequest(String name, String city, String state) {

    /**
     * <p>createBrewer.</p>
     *
     * @param id the id of the brewer in the database
     * @return a {@link org.redlich.data.Brewer} object
     */
    public Brewer createBrewer(int id) {
        return Brewer.builder()
                .id(id)
                .name(name)
                .city(city)
                .state(state)
                .build();
        }
    }
