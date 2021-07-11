package com.txtbits.starwarsapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.txtbits.starwarsapi.model.Film;
import com.txtbits.starwarsapi.model.People;
import com.txtbits.starwarsapi.repository.PeopleRepository;
import com.txtbits.starwarsapi.util.ConvertJavaMapToJson;
import com.txtbits.starwarsapi.util.SwapiReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleRepository repository;

    @Override
    public List<People> getAllPeople() {
        return repository.findAll();
    }

    @Override
    public Optional<People> getPeople(Long id) {
        return repository.findById(id);
    }

    @Override
    public JsonNode loadPeopleFromSwapi() {
        List<People> people = new ArrayList<>();

        SwapiReader reader = new SwapiReader();
        try {
            people = reader.getPeople();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!people.isEmpty()) {
            repository.saveAll(people);
        }

        return null;
    }

    @Override
    public String showCounts() {
        ArrayList<Map<String, Object>> counts = new ArrayList();
        List<People> people = repository.findAll();
        people.forEach(p -> {
            Map<String, Object> personInfo = new HashMap<>();
            personInfo.put("person", p.getName());
            personInfo.put("numberOfFilms", p.getFilms().size());
            personInfo.put("filmNames", p.getFilms().stream().map(Film::getTitle).collect(Collectors.toSet()));
            counts.add(personInfo);
        });
        return ConvertJavaMapToJson.convert(counts);
    }

}
