package org.example.flight_details.repository;

import org.example.flight_details.model.FlightDetails;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface FlightDetailsRepository extends ReactiveMongoRepository<FlightDetails,String> {
    Flux<FlightDetails> findByFlightId(String flightId);
}
