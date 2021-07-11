package com.txtbits.starwarsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadServiceImpl implements LoadService {

    @Autowired
    private FilmService filmService;

    @Autowired
    private PeopleService peopleService;

    @Autowired StarshipService starshipService;

    @Override
    public void loadEntitiesFromSwapi() {
        filmService.loadFilmsFromSwapi();
        peopleService.loadPeopleFromSwapi();
        starshipService.loadStarshipsFromSwapi();
    }
}
