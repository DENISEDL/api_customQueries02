package com.example.api_custom.query02.controllers;

import com.example.api_custom.query02.entities.Flight;
import com.example.api_custom.query02.enums.StatusEnum;
import com.example.api_custom.query02.repositories.FlightRepository;
import com.example.api_custom.query02.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

    @PostMapping("/generaterandom")
    public ResponseEntity<List<Flight>> generateFlights(@RequestParam Integer number) {
        List<Flight> flights = flightService.generateFlights(number);
        return ResponseEntity.ok(flights);
    }


    @GetMapping("/allList")
    public ResponseEntity<List<Flight>> provisionFlights(@RequestParam Integer number) {
        List<Flight> flights = flightService.generateFlights(number);
        return ResponseEntity.ok(flights);
    }


    @GetMapping("/p1orp2")
    public ResponseEntity<List<Flight>> p1OrP2Flights() {
        List<Flight> flightsOnTime = flightService.findByDoubleStatus(StatusEnum.P1, StatusEnum.P2);
        return ResponseEntity.ok(flightsOnTime);
    }


    @GetMapping("/onTime")
    public ResponseEntity<List<Flight>> ontimeFlights() {
        List<Flight> flightsOnTime = flightService.findByStatusEnum(StatusEnum.ONTIME);
        return ResponseEntity.ok(flightsOnTime);
    }


    @GetMapping("/pagination")
    public Page<Flight> getAllFlights(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size)
    {
        return flightRepository.findAll(PageRequest.of(page, size, Sort.by("fromAirport").ascending()));
    }
}