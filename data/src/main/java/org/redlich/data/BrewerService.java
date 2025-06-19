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
 * <p>BrewerService class.</p>
 *
 * @author mpredli01
 * @version $Id: $Id
 */
@ApplicationScoped
public class BrewerService {

    @Inject
    BrewerRepository brewerRepository;

    @Inject
    BeerRepository beerRepository;

    /**
     * <p>Default constructor.</p>
     */
    public BrewerService() {
        }
        
    /**
     * <p>findById.</p>
     *
     * @param id the id of the breewer in the database
     * @return a {@link java.util.Optional} object
     */
    public Optional<Brewer> findById(int id) {
        return brewerRepository.findById(id);
        }

    /**
     * <p>listBrewers.</p>
     *
     * @return a {@link java.util.stream.Stream} object
     */
    public Stream<Brewer> listBrewers() {
        return brewerRepository.findAll();
        }

    /**
     * <p>listBrewersByNameLike.</p>
     *
     * @param name a {@link java.lang.String} object
     * @return a {@link java.util.stream.Stream} object
     */
    public Stream<Brewer> listBrewersByNameLike(String name) {
        return listBrewersByNameLike(name, Pageable.ofSize(Integer.MAX_VALUE)).stream();
        }

    /**
     * <p>listBrewersByNameLike.</p>
     *
     * @param name a {@link java.lang.String} object
     * @param pageRequest a {@link jakarta.data.page.Pageable} object
     * @return a {@link jakarta.data.page.Page} object
     */
    public Page<Brewer> listBrewersByNameLike(String name, Pageable pageRequest) {
        return brewerRepository.findByNameLike(name, pageRequest);
        }

    /**
     * <p>add.</p>
     *
     * @param brewer a {@link org.redlich.data.Brewer} object
     * @return a {@link org.redlich.data.Brewer} object
     */
    public Brewer add(Brewer brewer) {
        return brewerRepository.save(brewer);
        }

    /**
     * <p>remove.</p>
     *
     * @param id the id of the brewer in the database
     */
    public void remove(int id) {
        brewerRepository.findById(id)
                .ifPresent(brewer -> {
                    beerRepository.deleteByBrewerId(brewer.getId());
                    brewerRepository.remove(brewer);
                });
        }

    /**
     * <p>removeAll.</p>
     */
    public void removeAll() {
        brewerRepository.deleteAll();
        }
    }
