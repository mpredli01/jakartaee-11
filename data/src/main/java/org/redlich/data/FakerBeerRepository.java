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

import jakarta.data.repository.PageableRepository;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Param;

import java.util.Set;

@Repository
public interface FakerBeerRepository extends PageableRepository<FakerBeer, String> {

    Set<FakerBeer> findByName(String name);

    Set<FakerBeer> findByHop(String hop);

    Set<FakerBeer> findByMalt(String malt);

    Set<FakerBeer> findByStyle(String style);

    Set<FakerBeer> findByYeast(String yeast);

    @Query("select * from FakerBeer where name = @name")
    Set<FakerBeer> query(@Param("name") String name);
    }