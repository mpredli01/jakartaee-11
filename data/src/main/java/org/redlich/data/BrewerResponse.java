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
 * <p>BrewerResponse class.</p>
 *
 * @param id the primary key.
 * @param name the name of the brewer.
 * @param city the city where the brewer is located.
 * @param state the state where the brewer is located.
 * @author mpredli01
 */
public record BrewerResponse(int id, String name, String city, String state) {

    /**
     * <p>of.</p>
     *
     * @param brewer a {@link org.redlich.data.Brewer} object
     * @return a {@link org.redlich.data.BrewerResponse} object
     */
    static BrewerResponse of(Brewer brewer) {
        return new BrewerResponse(brewer.getId(), brewer.getName(), brewer.getCity(), brewer.getState());
        }
    }
