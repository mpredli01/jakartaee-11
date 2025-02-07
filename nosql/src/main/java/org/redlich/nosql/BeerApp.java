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
// import jakarta.nosql.document.DocumentTemplate;
import jakarta.nosql.Template;

import org.eclipse.jnosql.mapping.DatabaseQualifier;

import java.util.List;
import java.util.stream.Stream;
import java.util.Optional;

public class BeerApp {
    public static void main(String[] args) {

        String title = "[APP] Welcome to the Jakarta NoSQL Demo Application";
        displayTitle(title);

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            final String brewerName = "Apponaug Brewing";
            final String beerName = "Golden Boi";

            // placeholders variables
            int brewer_id = 1;
            int beer_id = 5;

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

            Beer beer = Beer.builder()
                    .id((int)noOfBeers + 1)
                    .name(beerName)
                    .type(BeerType.IPA)
                    .brewer_id(brewer_id)
                    .abv(8.0)
                    .build();

            Brewer brewer = Brewer.builder()
                    .id((int)noOfBrewers + 1)
                    .name(brewerName)
                    .city("Warwick")
                    .state("Rhode Island")
                    .build();

            Brewer brewerTest = brewerRepository.findByName(brewerName);
            System.out.println("!!!! " + brewerTest);
            if(brewerTest != null)
                System.out.println("[APP] Saving " + brewerName + " in the database" );
                // brewerRepository.save(brewer);
            else
                System.out.println("[APP] This brewer already exists in the database");

            // beerRepository.save(beer);
            // brewerRepository.save(brewer);

            Template template = container.select(Template.class).get(); // DocumentTemplate

            /*
            Beer beer = template.insert(beer01);
            System.out.println("Beer saved" + beer);
             */
            
            // List<Beer> beerList = beerRepository.findByName("Pumking");

            Optional<Beer> beerOptional = template.select(Process.class)
                    .where("id").eq(beer_id).singleResult();
            System.out.println("[APP] Entity found (Optional): " + beerOptional);

            List<Beer> beered = template.<Beer>select(Beer.class)
                    .where("id").eq(beer_id)
                    .result();
            System.out.println("[APP] Entity found: " + beered);
            }
        }

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