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

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.nosql.Template;

import org.eclipse.jnosql.mapping.DatabaseQualifier;

import java.util.List;
import java.util.stream.Stream;
import java.util.Optional;

/**
 * <p>BeerApp class.</p>
 *
 * @author mpredli01
 */
public class BeerApp {
    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects
     */
    public static void main(String[] args) {

        String title = "[APP] Welcome to the Jakarta NoSQL Demo Application";
        displayTitle(title);

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            BeerRepository beerRepository = container.select(BeerRepository.class)
                    .select(DatabaseQualifier.ofDocument()).get();
            BrewerRepository brewerRepository = container.select(BrewerRepository.class)
                    .select(DatabaseQualifier.ofDocument()).get();

            Stream<Beer> beerCount = beerRepository.findAll();
            Stream<Brewer> brewerCount = brewerRepository.findAll();
            long noOfBeers = beerCount.count();
            long noOfBrewers = brewerCount.count();
            System.out.println("[APP] There are " + noOfBeers + " beers in the database");
            System.out.println("[APP] There are " + noOfBrewers + " brewers in the database");

            Stream<Beer> allBeers = beerRepository.findAll();
            Stream<Brewer> allBrewers = brewerRepository.findAll();
            allBeers.forEach(beer -> System.out.println("[APP] " + beer));
            allBrewers.forEach(brewer -> System.out.println("[APP] " + brewer));

            final String brewerName = "Redlich Brewing";
            final String beerName = "Colonial IPA";
            final String city = "Williamsburg";
            final String state = "Virginia";

            int brewerId = (int)noOfBrewers + 1;
            int beerId = (int)noOfBeers + 1;
            BeerType type = BeerType.IPA;
            double abv = 10.0;

            Brewer brewer = Brewer.builder()
                    .id(brewerId)
                    .name(brewerName)
                    .city(city)
                    .state(state)
                    .build();

            Beer beer = Beer.builder()
                    .id(beerId)
                    .name(beerName)
                    .type(type)
                    .brewer_id(brewerId)
                    .abv(abv)
                    .build();

            
            brewerRepository.save(brewer);
            beerRepository.save(beer);


            /*/
            // Despite being null, the conditional still moves to the else clause
            Brewer brewerTest = brewerRepository.findByName(brewerName);
            System.out.println("[APP] brewerTest = " + brewerTest);
            if(brewerTest != null) {
                System.out.println("[APP] Saving " + brewerName + " in the database");
                brewerRepository.save(brewer);
                }
            else
                System.out.println("[APP] This brewer already exists in the database");

            Beer beerTest = beerRepository.findByName(beerName);
            System.out.println("[APP] beerTest = " + beerTest);
            if(beerTest != null) {
                System.out.println("[APP] Saving " + beerName + " in the database");
                beerRepository.save(beer);
                }
            else
                System.out.println("[APP] This beer already exists in the database");
            /*/

            Template template = container.select(Template.class).get();

            // Brewer brewer01 = template.insert(brewer);
            // System.out.println("[APP] Saving " + brewer01 + " in the database");

            // Beer beer01 = template.insert(beer);
            // System.out.println("[APP] Saving " + beer01 + " in the database");

            Optional<Beer> beerOptional = template.select(Process.class)
                    .where("id").eq(beerId).singleResult();
            System.out.println("[APP] Entity found (Optional): " + beerOptional);

            List<Beer> beered = template.<Beer>select(Beer.class)
                    .where("id").eq(beerId)
                    .result();
            System.out.println("[APP] Entity found: " + beered);
            }
        }

    /**
     * <p>displayTitle.</p>
     *
     * @param title a {@link java.lang.String} object
     */
    public static void displayTitle(String title) {
        int length = title.length();
        System.out.print("[APP] ");
        for(int i = 0; i < length; ++i) {
            System.out.print("-");
            }
        System.out.println();
        System.out.println(title);
        System.out.print("[APP] ");
        for(int i = 0; i < length; ++i) {
            System.out.print("-");
            }
        System.out.println();
        }
    }
