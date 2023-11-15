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
public interface BeerRepository extends PageableRepository<Beer, Integer> {

    Set<Beer> findByName(String beer);

    Set<Beer> findByBrewerId(int brewer_id);

    void deleteById(int id);

    @Query("select * from Beer where name = @name")
    Set<Beer> query(@Param("name") String name);
    }
