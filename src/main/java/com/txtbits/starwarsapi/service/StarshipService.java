package com.txtbits.starwarsapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.txtbits.starwarsapi.model.Starship;

import java.util.List;
import java.util.Optional;

public interface StarshipService {

    List<Starship> getAllStarships();

    Optional<Starship> getStarship(Long id);

    JsonNode loadStarshipsFromSwapi();
}
