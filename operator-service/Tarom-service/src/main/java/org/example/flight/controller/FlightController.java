package org.example.flight.controller;

import org.example.flight.dto.FlightDto;
import org.example.flight.dto.FlightResponseDto;
import org.example.flight.model.Flight;
import org.example.flight.service.FlightService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;


@RestController
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/all")
    public Flux<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/search-by-id/{id}")
    public Mono<Flight> getFlightById(@PathVariable String id) {
        return flightService.getFlightById(id);
    }

    @PostMapping("/add")
    public Mono<Flight> saveFlight(@RequestBody FlightDto flightDTO) {
        return flightService.createFlight(flightDTO);
    }

    @PutMapping("/update")
    public Mono<Flight> updateFlight(@RequestBody FlightDto flightDTO) {
        return flightService.updateFlight(flightDTO);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteFlight(@PathVariable String id) {
        return flightService.deleteFlight(id);
    }

    @GetMapping("/flight-search")
    public Flux<FlightResponseDto> getByDateRangeAndStartAndEndDestination(ServerWebExchange exchange,
                                                                           @RequestParam("departure") String departure,
                                                                           @RequestParam("destination") String destination,
                                                                           @RequestParam("date") LocalDate date) {
        String operatorName = extractOperatorNameFromBasePath(exchange);
        return flightService.getByDepartureDestinationAndDate(operatorName, departure, destination, date);
    }

    private String extractOperatorNameFromBasePath(ServerWebExchange exchange) {
        String basePath = exchange.getRequest().getPath().contextPath().value();
        // Extrage numele operatorului din calea de bază
        // Exemplu: /tarom/flight-search -> "tarom"
        String[] parts = basePath.split("/");
        if (parts.length >= 2) {
            return parts[1]; // Operatorul este primul element din calea de bază
        } else {
            throw new IllegalArgumentException("Operator not found in base path");
        }

    }
}
