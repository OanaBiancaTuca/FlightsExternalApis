package org.example.flight_details.service;

import org.example.flight_details.dto.FlightDetailsDto;
import org.example.flight_details.model.FlightDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightDetailsService {

    Flux<FlightDetails> getAllFlightDetails();

    Mono<FlightDetails> getFlightDetailsById(String id);

    Mono<FlightDetails> createFlightDetails(FlightDetailsDto flightDetailsDTO);

    Mono<FlightDetails> updateFlightDetails(FlightDetailsDto flightDetailsDTO);

    Mono<Void> deleteFlightDetails(String id);
    Flux<FlightDetails> getByFlightId(String flightId);
}
