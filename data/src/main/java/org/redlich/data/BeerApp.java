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

import jakarta.data.repository.Page;
import jakarta.data.repository.Pageable;
import jakarta.data.repository.Sort;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.util.Optional;
import java.util.Set;

public class BeerApp {
    
    public BeerApp() {
        }

    public static void main(String[] args) {
        BeerApp app = new BeerApp();

        String title = "[APP] Welcome to the Beer Application using Jakarta Data";
        app.displayTitle(title);

        try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            BeerRepository beerRepository = container.select(BeerRepository.class).get();
            BrewerRepository brewerRepository = container.select(BrewerRepository.class).get();

            long noOfBeers = beerRepository.count();
            long noOfBrewers = brewerRepository.count();

            app.displayTitle("[APP] Let's start by obtaining the number of documents in the Beer and Brewer collections:");
            System.out.println("[APP] There are " + noOfBeers + " beers in the Beer collection");
            System.out.println("[APP] There are " + noOfBrewers + " brewers in the Brewer collection");

            app.delay(3000);

            Brewer brewer01 = Brewer.builder()
                    .id((int)noOfBrewers + 1)
                    .name("Narragansett Brewing")
                    .city("Providence")
                    .state("Rhode Island")
                    .build();
            // brewerRepository.save(brewer01);

            Beer beer01 = Beer.builder()
                    .id((int)noOfBeers + 1)
                    .name("Lunch")
                    .type(BeerType.IPA)
                    .brewer_id(33) // Maine Beer Company
                    .abv(7.0)
                    .build();
            // beerRepository.save(beer01);

            Beer beer02 = Beer.builder()
                    .id((int)noOfBeers + 2)
                    .name("Caramel Pumpkin Imperial Ale")
                    .type(BeerType.ALE)
                    .brewer_id(1) // Southern Tier
                    .abv(8.6)
                    .build();
            // beerRepository.save(beer02);

            app.displayTitle("[APP] Let's find beers by using a query named parameter:");
            beerRepository.query("Pumking").forEach(System.out::println);
            brewerRepository.query("Apponaug Brewing").forEach(System.out::println);

            app.delay(3000);

            app.displayTitle("[APP] Let's find a beer by its primary key:");
            Optional<Beer> id = beerRepository.findById(5);
            System.out.println("[APP] " + id);

            app.displayTitle("[APP] Let's find a beer by its name");
            Set<Beer> beerByName = beerRepository.findByName("Power Plant Amber Lager");
            beerByName.forEach(System.out::println);

            app.displayTitle("[APP] Let's find a beer by its `brewer_id`:");
            Set<Beer> beerByBrewer = beerRepository.findByBrewerId(1);
            beerByBrewer.forEach(System.out::println);

            app.delay(3000);

            app.displayTitle("[APP] Let's find all beers and paginate with ascending sort:");
            Pageable page = Pageable.ofPage(1).sortBy(Sort.asc("name"));

            app.displayTitle("[APP] Here is page 1:");
            Page<Beer> page1 = beerRepository.findAll(page);
            page1.forEach(System.out::println);

            app.displayTitle("[APP] Here is page 2:");
            Pageable secondPage = page.next();
            Page<Beer> page2 = beerRepository.findAll(secondPage);
            page2.forEach(System.out::println);

            app.displayTitle("[APP] Here is page 3:");
            Pageable thirdPage = secondPage.next();
            Page<Beer> page3 = beerRepository.findAll(thirdPage);
            page3.forEach(System.out::println);

            app.delay(3000);

            app.displayTitle("[APP] Let's find all brewers:");
            Pageable brewerPage = Pageable.ofSize(40).sortBy(Sort.asc("name"));
            app.displayTitle("[APP] Here is page 4:");
            Page<Brewer> page4 = brewerRepository.findAll(brewerPage);
            page4.forEach(System.out::println);

            app.delay(3000);
            
            app.displayTitle("[APP] Let's delete a beer by its primary key:");
            beerRepository.deleteById(31);

            app.displayTitle("[APP] Let's delete a brewer by its primary key:");
            brewerRepository.deleteById(35);

            app.displayTitle("[APP] COMING SOON: Let's update a document in the database");
            /*/
            TO-DO: add an update method
            /*/
            }
        }

    public void displayTitle(String title) {
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

    public void delay(int time) {
        try {
            Thread.sleep(time);
            }
        catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            }
        }
    }
    