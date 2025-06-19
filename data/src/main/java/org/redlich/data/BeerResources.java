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

import jakarta.data.Sort;
import jakarta.data.page.Pageable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

/**
 * <p>BeerResources class.</p>
 *
 * @author mpredli01
 * @version $Id: $Id
 */
@Path("beer")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BeerResources {

    @Inject
    BeerService beerService;

    /**
     * <p>Default constructor.</p>
     */
    public BeerResources() {
        }

    /**
     * <p>findById.</p>
     *
     * @param id - the id
     * @return a {@link org.redlich.data.BeerResponse} object
     */
    @GET
    @Path("/{id}")
    public BeerResponse findById(@PathParam("id") int id) {
        return beerService.findById(id)
                .map(BeerResponse::of)
                .orElseThrow(() -> new NotFoundException());
        }

    /**
     * <p>listBeers.</p>
     *
     * @return a {@link java.util.List} object
     */
    @GET
    public List<BeerResponse> listBeers(){
        return beerService.listBeers().map(BeerResponse::of).toList();
        }

    /**
     * <p>listBeersByBrewer.</p>
     *
     * @param brewerName a {@link java.lang.String} object
     * @return a {@link java.util.List} object
     */
    @GET
    @Path("/brewer/{brewer}")
    public List<BeerResponse> listBeersByBrewer(@PathParam("brewer") String brewerName){
        return beerService.listBeersByBrewer(brewerName)
                .map(BeerResponse::of).toList();
        }

    /**
     * <p>listBeersByBrewer.</p>
     *
     * @param brewerName a {@link java.lang.String} object
     * @param pageNum a long
     * @return a {@link java.util.List} object
     */
    @GET
    @Path("/brewer/{brewer}/page/{pageNum}")
    public List<BeerResponse> listBeersByBrewer(@PathParam("brewer") String brewerName,
                                                @PathParam("pageNum") long pageNum) {
        Pageable pageRequest = Pageable.ofSize(5)
                .page(pageNum)
                .sortBy(Sort.asc("name"), Sort.asc("id"));
        return beerService.listBeersByBrewer(brewerName, pageRequest).stream()
                .map(BeerResponse::of).toList();
        }

    /**
     * <p>add.</p>
     *
     * @param id the id
     * @param request a {@link org.redlich.data.BeerRequest} object
     * @return a {@link org.redlich.data.BeerResponse} object
     */
    @POST
    @Path("/{id}")
    public BeerResponse add(@PathParam("id") int id, BeerRequest request) {
        return BeerResponse.of(beerService.add(request.createBeer(id)));
        }

    /**
     * <p>remove.</p>
     *
     * @param id the id
     */
    @DELETE
    @Path("/{id}")
    public void remove(@PathParam("id") int id){
        beerService.remove(id);
        }

    /**
     * <p>removeAll.</p>
     */
    @DELETE
    public void removeAll(){
        beerService.removeAll();
        }
    }
