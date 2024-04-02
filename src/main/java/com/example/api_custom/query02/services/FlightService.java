package com.example.api_custom.query02.services;

import com.example.api_custom.query02.entities.Flight;
import com.example.api_custom.query02.enums.StatusEnum;
import com.example.api_custom.query02.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service

public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
    List<Flight> flights = new ArrayList<>();

    /**
     * Generiamo la lista di voli in random
     * @param index se il numero del volo è nullo o 0,generiamo 100 voli
     * @return la lista dei voli in random
     */

    public List<Flight> generateFlights(Integer index) {
        Random random = new Random();
        if (index == null || index == 0) {
            index = 100;
        }
        for (int i = 0; i < index; i++) {
            Flight flight = new Flight();
            flight.setDescription("Flight " + random.nextInt(1000));
            flight.setFromAirport("Airport " + random.nextInt(100));
            flight.setToAirport("Airport " + random.nextInt(100));
            flight.setStatusEnum(StatusEnum.values()[random.nextInt(StatusEnum.values().length)]);
            flights.add(flight);
            flightRepository.saveAndFlush(flight);
        }
        return flights;
    }

    /**
     *
     * @param enumToFind cerchiamo lo stato
     * @return la lista dei voli con lo stato specificato
     */
    public List<Flight> findByStatusEnum(StatusEnum enumToFind) {
        List<Flight> flightsWithStatus = new ArrayList<>();
        for (Flight flights : flights) {
            if (flights.getStatusEnum() == enumToFind) {
                flightsWithStatus.add(flights);
            }
        }
        return flightsWithStatus;
    }

    /**
     *
     * @param enumToFind cerchiamo lo stato di un volo
     * @param enumToFind2 cerchiamo lo stato di un altro volo
     * @return la lista dei voli con gli stati specificati
     */
    public List<Flight> findByDoubleStatus(StatusEnum enumToFind, StatusEnum enumToFind2) {
        List<Flight> flightsWithStatus = new ArrayList<>();
        for (Flight flights : flights) {
            if (flights.getStatusEnum() == enumToFind || flights.getStatusEnum() == enumToFind2) {
                flightsWithStatus.add(flights);
            }
        }
        return flightsWithStatus;
    }
}
