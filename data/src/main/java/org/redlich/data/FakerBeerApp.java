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

import com.github.javafaker.Faker;

import jakarta.data.repository.Page;
import jakarta.data.repository.Pageable;
import jakarta.data.repository.Sort;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.util.Optional;
import java.util.Set;

public class FakerBeerApp {

    public FakerBeerApp() {
        }

    public static void main(String[] args) {
        FakerBeerApp app = new FakerBeerApp();
        Faker faker = new Faker();

        String title = "[APP] Welcome to the Beer Application using Jakarta Data";
        app.displayTitle(title);

        try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            FakerBeerRepository repository = container.select(FakerBeerRepository.class).get();
            for(int i = 0; i < 10; ++i) {
                FakerBeer fakerBeer = FakerBeer.of(faker);
                // repository.save(fakerBeer);
                }

            app.displayTitle("[APP] Use Query Named Parameter");
            repository.query("Two Hearted Ale").forEach(System.out::println);

            app.displayTitle("[APP] Use findBy Methods");
            Optional<FakerBeer> id = repository.findById("6e5c6f3c-6123-4b0c-92a2-aaa02c3ed246");
            System.out.println("[APP] Find by id: " + id);

            Set<FakerBeer> name = repository.findByName("Bourbon County Stout");
            System.out.println("[APP] Find by name: " + name);

            Set<FakerBeer> hop = repository.findByHop("Nugget");
            System.out.println("[APP] Find by hop: " + hop);

            Set<FakerBeer> malt = repository.findByMalt("Victory");
            System.out.println("[APP] Find by malt: " + malt);

            Set<FakerBeer> style = repository.findByStyle("Strong Ale");
            System.out.println("[APP] Find by style: " + style);

            Set<FakerBeer> yeast = repository.findByYeast("3787 - Trappist High Gravity");
            System.out.println("[APP] Find by yeast" + yeast);

            app.displayTitle("[APP] Pagination");
            Pageable page = Pageable.ofPage(1).sortBy(Sort.asc("yeast"));
            Page pages = repository.findAll(page);
            System.out.println("[APP] Page 1:");
            pages.forEach(System.out::println);
            Pageable nextPage = page.next();
            int i = 2;
            while(nextPage != null) {
                System.out.println("[APP] Page " + i + ":");
                pages.forEach(System.out::println);
                ++i;
                nextPage = page.next();
                System.out.println();
                try {
                    Thread.sleep(5000);
                    }
                catch(InterruptedException exception) {
                    }
                }


            /*/
            System.out.println("[APP] Page 1:");
            page1.forEach(System.out::println);
            System.out.println("[APP] Page 2:");
            Pageable secondPage = page.next();
            Page<Beer> page2 = repository.findAll(secondPage);
            page2.forEach(System.out::println);
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
    }
