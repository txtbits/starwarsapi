package com.txtbits.starwarsapi.controller;

import com.txtbits.starwarsapi.model.Film;
import com.txtbits.starwarsapi.service.FilmService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FilmController {

    @Autowired
    FilmService filmService;

    @ApiOperation(value = "Get all films", notes = "Get all films in database")
    @GetMapping("/film")
    public ResponseEntity<List<Film>> getAllOrders() {
        try {
            List<Film> orders = filmService.getAllFilms();
            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get a film", notes = "Get a film in database by identifier")
    @RequestMapping(value = "/film/{id}", method = RequestMethod.GET)
    public ResponseEntity<Film> getFilm(
            @ApiParam(name = "id", value = "Identifier of a order", required = true)
            @PathVariable("id") Long id) {
        try {
            Optional<Film> order = filmService.getFilm(id);
            if (order.isPresent()) {
                return new ResponseEntity<>(order.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
