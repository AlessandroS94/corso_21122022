package it.myproject.springboot_jwt_angular.controller;

import it.myproject.springboot_jwt_angular.model.Flight;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flights")

public class FlightsController {

    @GetMapping()
    public ResponseEntity<?> findAllFlights() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(1,1,"PESCARA","ROMA"));
        flights.add(new Flight(1,1,"MILANO","ROMA"));
        flights.add(new Flight(1,1,"ROMA","PESCARA"));
        flights.add(new Flight(1,1,"PESCARA","BOLOGNA"));
        flights.add(new Flight(1,1,"PESCARA","PARIGI"));

        return new ResponseEntity<>(flights,HttpStatus.OK);
    }
}
