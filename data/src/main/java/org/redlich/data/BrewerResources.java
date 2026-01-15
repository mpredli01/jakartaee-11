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
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

/**
 * <p>BrewerResources class.</p>
 *
 * @author mpredli01
 * @version $Id: $Id
 */
@ApplicationScoped
@Path("brewer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BrewerResources {

    @Inject
    BrewerService brewerService;

    /**
     * <p>Default constructor.</p>
     */
    public BrewerResources() {
        }

    /**
     * <p>findById.</p>
     *
     * @param id the id of the brewer in the database
     * @return a {@link org.redlich.data.BrewerResponse} object
     */
    @GET
    @Path("/{id}")
    public BrewerResponse findById(@PathParam("id") int id) {
        return brewerService.findById(id)
                .map(BrewerResponse::of)
                .orElseThrow(() -> new NotFoundException());
        }

    /**
     * <p>listBrewers.</p>
     *
     * @return a {@link java.util.List} object
     */
    @GET
    public List<BrewerResponse> listBrewers() {
        return brewerService.listBrewers()
                .map(BrewerResponse::of)
                .toList();
        }

    /**
     * <p>listBrewerByName.</p>
     *
     * @param name a {@link java.lang.String} object
     * @return a {@link java.util.List} object
     */
    @GET
    @Path("/brewer/{name}")
    public List<BrewerResponse> listBrewerByName(@PathParam("name") String name) {
        return brewerService.listBrewersByNameLike(name)
                .map(BrewerResponse::of)
                .toList();
        }

    /**
     * <p>listBrewerByName.</p>
     *
     * @param name a {@link java.lang.String} object
     * @param pageNum a long
     * @return a {@link java.util.List} object
     */
    @GET
    @Path("/brewer/{name}/page/{pageNum}")
    public List<BrewerResponse> listBrewerByName(@PathParam("name") String name,
                                                 @PathParam("pageNum") long pageNum) {
        Pageable pageRequest = Pageable.ofSize(5)
                .page(pageNum)
                .sortBy(Sort.asc("name"), Sort.asc("id"));
        return brewerService.listBrewersByNameLike(name, pageRequest)
                .stream().map(BrewerResponse::of).toList();
        }

    /**
     * <p>add.</p>
     *
     * @param id the id of the brewer in the database
     * @param request a {@link org.redlich.data.BrewerRequest} object
     * @return a {@link org.redlich.data.BrewerResponse} object
     */
    @POST
    @Path("/{id}")
    public BrewerResponse add(@PathParam("id") int id, BrewerRequest request) {
        var brewer = request.createBrewer(id);
        return BrewerResponse.of(brewerService.add(brewer));
        }

    /**
     * <p>remove.</p>
     *
     * @param id the id of the brewer in the database
     */
    @Path("/{id}")
    @DELETE
    public void remove(@PathParam("id") int id) {
        brewerService.remove(id);
        }

    /**
     * <p>removeAll.</p>
     */
    @DELETE
    public void removeAll() {
        brewerService.removeAll();
        }
    }
