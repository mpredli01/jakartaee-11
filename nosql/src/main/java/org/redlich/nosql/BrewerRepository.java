package org.redlich.nosql;

import jakarta.data.repository.PageableRepository;
import jakarta.data.repository.Repository;

import java.util.List;
import java.util.stream.Stream;


@Repository
public interface BrewerRepository extends PageableRepository<Brewer, String> {

    Stream<Brewer> findAll();
    Brewer findByName(String name);

    }
