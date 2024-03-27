package org.example.flight.repository;

import org.example.flight.model.Flight;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FlightRepository extends ReactiveMongoRepository<Flight,String> {
}
