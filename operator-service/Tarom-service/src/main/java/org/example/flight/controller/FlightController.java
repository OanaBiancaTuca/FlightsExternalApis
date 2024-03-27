package org.example.flight.controller;

import org.example.flight.dto.FlightDto;
import org.example.flight.model.Flight;
import org.example.flight.service.FlightService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/tarom")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/all")
    public Flux<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
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
    public Flux<Flight> getByDateRangeAndStartAndEndDestination(@RequestParam("departure") String departure,
                                                                   @RequestParam("destination") String destination,
                                                                   @RequestParam("date") LocalDate date) {
        return flightService.getByDepartureDestinationAndDate(departure, destination,date);
    }

}
