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
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>BeerService class.</p>
 *
 * @author mpredli01
 * @version $Id: $Id
 */
@ApplicationScoped
public class BeerService {

    @Inject
    BeerRepository beerRepository;

    @Inject
    BrewerRepository brewerRepository;

    /**
     * <p>findById.</p>
     *
     * @param id the id
     * @return a {@link java.util.Optional} object
     */
    public Optional<Beer> findById(int id) {
        return beerRepository.findById(id);
        }

    /**
     * <p>listBeers.</p>
     *
     * @return a {@link java.util.stream.Stream} object
     */
    public Stream<Beer> listBeers() {
        return beerRepository.findAll();
        }

    /**
     * <p>listBeersByBrewer.</p>
     *
     * @param brewerName a {@link java.lang.String} object
     * @return a {@link java.util.stream.Stream} object
     */
    public Stream<Beer> listBeersByBrewer(String brewerName) {
        return beerRepository.findByBrewerIdIn(
                brewerRepository.findByNameLike(brewerName)
                        .map(Brewer::getId)
                        .toList());
        }

    /**
     * <p>listBeersByBrewer.</p>
     *
     * @param brewerName a {@link java.lang.String} object
     * @param pageable a {@link jakarta.data.page.Pageable} object
     * @return a {@link jakarta.data.page.Page} object
     */
    public Page<Beer> listBeersByBrewer(String brewerName, Pageable pageable) {
        return beerRepository.findByBrewerIdIn(
                brewerRepository.findByNameLike(brewerName)
                        .map(Brewer::getId)
                        .toList(), pageable);
        }

    /**
     * <p>add.</p>
     *
     * @param beer a {@link org.redlich.data.Beer} object
     * @return a {@link org.redlich.data.Beer} object
     */
    public Beer add(Beer beer) {
        return beerRepository.save(beer);
        }

    /**
     * <p>remove.</p>
     *
     * @param id the id
     */
    public void remove(int id) {
        beerRepository.findById(id)
                .ifPresent(beerRepository::remove);
        }

    /**
     * <p>removeAll.</p>
     */
    public void removeAll() {
        beerRepository.deleteAll();
        }
    }
