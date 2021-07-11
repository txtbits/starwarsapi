package com.txtbits.starwarsapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.txtbits.starwarsapi.model.Film;
import com.txtbits.starwarsapi.repository.FilmRepository;
import com.txtbits.starwarsapi.util.SwapiReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository repository;

    @Override
    public List<Film> getAllFilms() {
        return repository.findAll();
    }

    @Override
    public Optional<Film> getFilm(Long id) {
        return repository.findById(id);
    }

    @Override
    public JsonNode loadFilmsFromSwapi() {
        List<Film> films = new ArrayList<>();

        SwapiReader reader = new SwapiReader();
        try {
            films = reader.getFilms();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!films.isEmpty()) {
            repository.saveAll(films);
        }

        return null;
    }
}
