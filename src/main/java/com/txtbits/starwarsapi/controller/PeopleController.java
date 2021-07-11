package com.txtbits.starwarsapi.controller;

import com.txtbits.starwarsapi.model.Film;
import com.txtbits.starwarsapi.model.People;
import com.txtbits.starwarsapi.service.FilmService;
import com.txtbits.starwarsapi.service.PeopleService;
import com.txtbits.starwarsapi.util.ConvertJavaMapToJson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class PeopleController {

    @Autowired
    PeopleService peopleService;

    @Autowired
    FilmService filmService;

    @ApiOperation(value = "Get all persons", notes = "Get people in database")
    @GetMapping("/people")
    public ResponseEntity<List<People>> getAllOrders() {
        try {
            List<People> people = peopleService.getAllPeople();
            if (people.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(people, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get a person", notes = "Get a person in database by identifier")
    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<People> getPeople(
            @ApiParam(name = "id", value = "Identifier of a order", required = true)
            @PathVariable("id") Long id) {
        try {
            Optional<People> order = peopleService.getPeople(id);
            if (order.isPresent()) {
                return new ResponseEntity<>(order.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ApiOperation(value = "Counts by appearances", notes = "Get counts by appearances")
    @RequestMapping(value = "/people/counts-appearances", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> showCountsAppearances() {
        String json = peopleService.showCounts();
        if (json != null) {
            return new ResponseEntity<>(json, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Counts by films", notes = "Get counts by films")
    @RequestMapping(value = "/people/counts-by-films", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> showCountsByFilms(@RequestParam(value = "films") String films) {
        List<People> people = peopleService.getAllPeople();
        List<People> peopleWithMostAppearances = new ArrayList<>();

        List<Long> filmsIdSelected = Stream.of(films.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        for (People person: people) {
            List<Long> filmsByPerson = person.getFilms().stream().map(Film::getId).collect(Collectors.toList());
            if (filmsByPerson.containsAll(filmsIdSelected)) {
                peopleWithMostAppearances.add(person);
            }
        }
        if (!peopleWithMostAppearances.isEmpty()) {
            String json = ConvertJavaMapToJson.convert(peopleWithMostAppearances.toArray());
            if (json != null) {
                return new ResponseEntity<>(json, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

}
