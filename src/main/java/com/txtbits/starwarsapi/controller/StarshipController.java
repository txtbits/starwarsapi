package com.txtbits.starwarsapi.controller;

import com.txtbits.starwarsapi.model.Starship;
import com.txtbits.starwarsapi.service.StarshipService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StarshipController {

    @Autowired
    StarshipService starshipService;

    @ApiOperation(value = "Get all starships", notes = "Get starship in database")
    @GetMapping("/starship")
    public ResponseEntity<List<Starship>> getAllOrders() {
        try {
            List<Starship> starship = starshipService.getAllStarships();
            if (starship.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(starship, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get a starship", notes = "Get a starship in database by identifier")
    @RequestMapping(value = "/starship/{id}", method = RequestMethod.GET)
    public ResponseEntity<Starship> getStarship(
            @ApiParam(name = "id", value = "Identifier of a order", required = true)
            @PathVariable("id") Long id) {
        try {
            Optional<Starship> order = starshipService.getStarship(id);
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
