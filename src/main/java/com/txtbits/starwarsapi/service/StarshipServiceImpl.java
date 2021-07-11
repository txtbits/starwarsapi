package com.txtbits.starwarsapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.txtbits.starwarsapi.model.Starship;
import com.txtbits.starwarsapi.repository.StarshipRepository;
import com.txtbits.starwarsapi.util.SwapiReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StarshipServiceImpl implements StarshipService {

    @Autowired
    private StarshipRepository repository;

    @Override
    public List<Starship> getAllStarships() {
        return repository.findAll();
    }

    @Override
    public Optional<Starship> getStarship(Long id) {
        return repository.findById(id);
    }

    @Override
    public JsonNode loadStarshipsFromSwapi() {
        List<Starship> starships = new ArrayList<>();

        SwapiReader reader = new SwapiReader();
        try {
            starships = reader.getStarships();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!starships.isEmpty()) {
            repository.saveAll(starships);
        }

        return null;
    }
}
