package org.example.flight.service;


import org.example.flight.dto.FlightDto;
import org.example.flight.dto.FlightResponseDto;
import org.example.flight.model.Flight;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface FlightService {

    Flux<Flight> getAllFlights();

    Mono<Flight> getFlightById(String id);

    Mono<Flight> createFlight(FlightDto flightDTO);

    Mono<Flight> updateFlight(FlightDto flightDTO);

    Mono<Void> deleteFlight(String id);

    Flux<FlightResponseDto> getByDepartureDestinationAndDate(String operatorName,String departure, String destination, LocalDate date);
}
