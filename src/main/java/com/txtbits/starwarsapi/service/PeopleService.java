package com.txtbits.starwarsapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.txtbits.starwarsapi.model.People;

import java.util.List;
import java.util.Optional;

public interface PeopleService {

    List<People> getAllPeople();

    Optional<People> getPeople(Long id);

    JsonNode loadPeopleFromSwapi();

    String showCounts();
}
