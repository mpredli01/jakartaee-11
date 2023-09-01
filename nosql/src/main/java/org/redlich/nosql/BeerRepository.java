package org.redlich.nosql;

import jakarta.data.repository.PageableRepository;
import jakarta.data.repository.Repository;

import java.util.List;
import java.util.stream.Stream;


@Repository
public interface BeerRepository extends PageableRepository<Beer, Long> {

    Stream<Beer> findAll();
    Beer findByName(String name);

    }
