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
import jakarta.data.repository.Save;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>BrewerRepository interface.</p>
 *
 * @author mpredli01
 * @version $Id: $Id
 */
@Repository
public interface BrewerRepository extends DataRepository<Brewer, Integer> {

    /**
     * <p>findAll.</p>
     *
     * @return a {@link java.util.stream.Stream} object
     */
    Stream<Brewer> findAll();

    /**
     * <p>findById.</p>
     *
     * @param id the id of the brewer in the database
     * @return a {@link java.util.Optional} object
     */
    Optional<Brewer> findById(int id);

    /**
     * <p>findByNameLike.</p>
     *
     * @param city a {@link java.lang.String} object
     * @return a {@link java.util.stream.Stream} object
     */
    Stream<Brewer> findByNameLike(String city);

    /**
     * <p>findByNameLike.</p>
     *
     * @param name a {@link java.lang.String} object
     * @param pageable a {@link jakarta.data.page.Pageable} object
     * @return a {@link jakarta.data.page.Page} object
     */
    Page<Brewer> findByNameLike(String name, Pageable pageable);

    /**
     * <p>save.</p>
     *
     * @param brewer a {@link org.redlich.data.Brewer} object
     * @return a {@link org.redlich.data.Brewer} object
     */
    @Save
    Brewer save(@Valid Brewer brewer);

    /**
     * <p>remove.</p>
     *
     * @param brewer a {@link org.redlich.data.Brewer} object
     */
    @Delete
    void remove(Brewer brewer);

    /**
     * <p>deleteAll.</p>
     */
    @Query("delete from Brewer")
    void deleteAll();
    }

