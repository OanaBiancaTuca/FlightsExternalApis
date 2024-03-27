package org.example.flight.repository;

import org.example.flight.model.Flight;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightRepository extends ReactiveMongoRepository<Flight,String> {
    Flux<Flight> findByDepartureAndDestination(String departure, String destination);
}
