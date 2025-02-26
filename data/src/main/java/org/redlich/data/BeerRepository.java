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

import jakarta.data.page.Page;
import jakarta.data.page.Pageable;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Repository;
import jakarta.data.repository.DataRepository;
import jakarta.data.repository.Query;
import jakarta.data.repository.Param;
import jakarta.data.repository.Save;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * <p>BeerRepository interface.</p>
 *
 * @author mpredli01
 * @version $Id: $Id
 */
@Repository
public interface BeerRepository extends DataRepository<Beer, Integer> {

    /**
     * <p>findAll.</p>
     *
     * @return a {@link java.util.stream.Stream} object
     */
    Stream<Beer> findAll();

    /**
     * <p>findById.</p>
     *
     * @param id a int
     * @return a {@link java.util.Optional} object
     */
    Optional<Beer> findById(int id);

    /**
     * <p>findByBrewerIdIn.</p>
     *
     * @param brewerIds a {@link java.util.List} object
     * @return a {@link java.util.stream.Stream} object
     */
    Stream<Beer> findByBrewerIdIn(List<Integer> brewerIds);

    /**
     * <p>findByBrewerIdIn.</p>
     *
     * @param brewerIds a {@link java.util.List} object
     * @param pageable a {@link jakarta.data.page.Pageable} object
     * @return a {@link jakarta.data.page.Page} object
     */
    Page<Beer> findByBrewerIdIn(List<Integer> brewerIds, Pageable pageable);

    /**
     * <p>save.</p>
     *
     * @param beer a {@link org.redlich.data.Beer} object
     * @return a {@link org.redlich.data.Beer} object
     */
    @Save
    Beer save(@Valid Beer beer);

    /**
     * <p>remove.</p>
     *
     * @param beer a {@link org.redlich.data.Beer} object
     */
    @Delete
    void remove(Beer beer);

    /**
     * <p>query.</p>
     *
     * @param name a {@link java.lang.String} object
     * @return a {@link java.util.Set} object
     */
    @Query("select * from Beer where name = @name")
    Set<Beer> query(@Param("name") String name);

    /**
     * <p>deleteAll.</p>
     */
    @Query("delete from Beer")
    void deleteAll();

    /**
     * <p>deleteByBrewerId.</p>
     *
     * @param brewerId a int
     */
    @Query("delete from Beer where brewerId = @brewerId")
    void deleteByBrewerId(@Param("brewerId") int brewerId);
    }
