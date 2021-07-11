package com.txtbits.starwarsapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.txtbits.starwarsapi.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {

    List<Film> getAllFilms();

    Optional<Film> getFilm(Long id);

    JsonNode loadFilmsFromSwapi();
}
