package com.txtbits.starwarsapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.txtbits.starwarsapi.model.Film;
import com.txtbits.starwarsapi.model.People;
import com.txtbits.starwarsapi.model.Starship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class SwapiReader {

    private final Logger log = LoggerFactory.getLogger(SwapiReader.class);

    private static String FILMS_ENDPOINT = "https://swapi.dev/api/films/";
    private static String PEOPLE_ENDPOINT = "https://swapi.dev/api/people/";
    private static String STARSHIPS_ENDPOINT = "https://swapi.dev/api/starships/";

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    private List<Film> films;
    private List<People> people;
    private List <Starship> starships;

    public List<Film> getFilms() throws IOException {
        if (films == null) {
            films = new LinkedList<>();
            processSwapiResultGeneric(FILMS_ENDPOINT, Film.class, films);
            log.info("Films loaded");
        }
        return films;
    }

    public List<People> getPeople() throws IOException {
        if (people == null) {
            people = new LinkedList<>();
            processSwapiResultGeneric(PEOPLE_ENDPOINT, People.class, people);
            log.info("Peoples loaded");
        }
        return people;
    }

    public List<Starship> getStarships() throws IOException {
        if (starships == null) {
            starships = new LinkedList<>();
            processSwapiResultGeneric(STARSHIPS_ENDPOINT, Starship.class, starships);
            log.info("Starships loaded");
        }
        return starships;
    }

    private void processSwapiResultGeneric(String endpoint, Class clazz, List<?> entityElements) throws IOException {
        JsonNode jsonNode = callSwapiRest(endpoint);
        int count = jsonNode.get("count").asInt();
        String next = jsonNode.get("next").asText(); // this node contains url resource + next page
        processSwapiResult(jsonNode, clazz);

        while (!"null".equalsIgnoreCase(next)) {
            log.info("Elements loaded {}", entityElements.size(), count);
            jsonNode = callSwapiRest(next);
            processSwapiResult(jsonNode, clazz);
            next = jsonNode.get("next").asText();
        }
    }

    private void processSwapiResult(JsonNode jsonNode, Class clazz) {
        JsonNode content = jsonNode.get("results");
        content.forEach(node -> {
            try {
                if (clazz.equals(Film.class)) {
                    Film film = (Film) objectMapper.treeToValue(node, clazz);
                    films.add(film);
                } else if (clazz.equals(People.class)) {
                    People person = (People) objectMapper.treeToValue(node, clazz);
                    List<Film> filmsByPerson = new ArrayList<>();
                    for (int i = 0; i < node.get("films").size(); i++) {
                        Film film = new Film();
                        String[] urlArray = node.get("films").get(i).toString().split("/");
                        Long id =  Long.parseLong(urlArray[urlArray.length-2]);
                        film.setId(id);
                        filmsByPerson.add(film);
                    }
                    person.setFilms(filmsByPerson);
                    /*List<Starship> starshipsByPerson = new ArrayList<>();
                    for (int i = 0; i < node.get("starships").size(); i++) {
                        Starship starship = new Starship();
                        String[] urlArray = node.get("starships").get(i).toString().split("/");
                        Long id =  Long.parseLong(urlArray[urlArray.length-2]);
                        starship.setId(id);
                        starshipsByPerson.add(starship);
                    }
                    person.setStarships(starshipsByPerson);*/
                    people.add(person);
                } else if (clazz.equals(Starship.class)) {
                    Starship starship = (Starship) objectMapper.treeToValue(node, clazz);
                    starships.add(starship);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    private JsonNode callSwapiRest(String url) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> forEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return objectMapper.readTree(forEntity.getBody());
    }
}
