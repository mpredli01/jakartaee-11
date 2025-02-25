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
package org.redlich.nosql;

// import jakarta.data.repository.PageableRepository;
import jakarta.data.repository.Repository;
import org.eclipse.jnosql.mapping.NoSQLRepository;

import java.util.List;
import java.util.stream.Stream;


/**
 * <p>BeerRepository interface.</p>
 *
 * @author mpredli01
 */
@Repository
public interface BeerRepository extends NoSQLRepository<Beer, Long> { // PageableRepository

    /**
     * <p>findAll.</p>
     *
     * @return a {@link java.util.stream.Stream} object
     */
    Stream<Beer> findAll();
    /**
     * <p>findByName.</p>
     *
     * @param name a {@link java.lang.String} object
     * @return a {@link org.redlich.nosql.Beer} object
     */
    Beer findByName(String name);

    }
