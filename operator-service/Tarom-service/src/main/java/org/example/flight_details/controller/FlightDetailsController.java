package org.example.flight_details.controller;

import org.example.flight_details.dto.FlightDetailsDto;
import org.example.flight_details.model.FlightDetails;
import org.example.flight_details.service.FlightDetailsService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tarom/flight-details")
public class FlightDetailsController {

    private final FlightDetailsService flightDetailsService;

    public FlightDetailsController(FlightDetailsService flightDetailsService) {
        this.flightDetailsService = flightDetailsService;
    }

    @GetMapping("/all")
    public Flux<FlightDetails> getAllFlightDetails() {
        return flightDetailsService.getAllFlightDetails();
    }

    @GetMapping("/{id}")
    public Mono<FlightDetails> getFlightDetailsById(@PathVariable String id) {
        return flightDetailsService.getFlightDetailsById(id);
    }

    @PostMapping("/add")
    public Mono<FlightDetails> saveFlightDetails(@RequestBody FlightDetailsDto flightDetailsDto) {
        return flightDetailsService.createFlightDetails(flightDetailsDto);
    }

    @PutMapping("/update")
    public Mono<FlightDetails> updateFlightDetails(@RequestBody FlightDetailsDto flightDetailsDto) {
        return flightDetailsService.updateFlightDetails(flightDetailsDto);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteFlightDetails(@PathVariable String id) {
        return flightDetailsService.deleteFlightDetails(id);
    }
    @GetMapping("/by-flight/{flightId}")
    public Flux<FlightDetails> getByFlightId(@PathVariable String flightId) {
        return flightDetailsService.getByFlightId(flightId);
    }
}