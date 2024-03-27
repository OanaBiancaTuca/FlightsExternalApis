package org.example.flight.service;

import org.example.flight.dto.FlightDto;
import org.example.flight.exception.FlightNotFoundException;
import org.example.flight.mapper.FlightMapper;
import org.example.flight.model.Flight;
import org.example.flight.repository.FlightRepository;
import org.example.flight_details.repository.FlightDetailsRepository;
import org.example.operator.exception.OperatorNotFoundException;
import org.example.operator.repository.OperatorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final OperatorRepository operatorRepository;
    private final FlightMapper flightMapper;
    private final FlightDetailsRepository flightDetailsRepository;

    public FlightServiceImpl(FlightRepository flightRepository, OperatorRepository operatorRepository,
                             FlightMapper flightMapper, FlightDetailsRepository flightDetailsRepository) {
        this.flightRepository = flightRepository;
        this.operatorRepository = operatorRepository;
        this.flightMapper = flightMapper;
        this.flightDetailsRepository = flightDetailsRepository;
    }

    @Override
    public Flux<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Mono<Flight> getFlightById(String id) {
        return flightRepository.findById(id)
                .switchIfEmpty(Mono.error(new FlightNotFoundException(id)));
    }

    @Override
    public Mono<Flight> createFlight(FlightDto flightDTO) {
        return operatorRepository.existsById(flightDTO.getIdOperator())
                .flatMap(operatorExists -> {
                    if (operatorExists) {
                        Flight flight = flightMapper.dtoToEntity(flightDTO);
                        return flightRepository.save(flight);
                    } else {
                        return Mono.error(new OperatorNotFoundException(flightDTO.getIdOperator()));
                    }
                });
    }

    @Override
    public Mono<Flight> updateFlight(FlightDto flightDTO) {
        return operatorRepository.existsById(flightDTO.getIdOperator())
                .flatMap(operatorExists -> {
                    if (operatorExists) {
                        return flightRepository.findById(flightDTO.getId())
                                .flatMap(existingFlight -> {
                                    existingFlight.setDeparture(flightDTO.getDeparture());
                                    existingFlight.setDestination(flightDTO.getDestination());
                                    existingFlight.setIdOperator(flightDTO.getIdOperator());
                                    return flightRepository.save(existingFlight);
                                })
                                .switchIfEmpty(Mono.error(new FlightNotFoundException(flightDTO.getId())));
                    } else {
                        return Mono.error(new OperatorNotFoundException(flightDTO.getIdOperator()));
                    }
                });
    }

    @Override
    public Mono<Void> deleteFlight(String id) {
        return flightRepository.existsById(id)
                .flatMap(flightExists -> {
                    if (flightExists) {
                        return flightRepository.deleteById(id);
                    } else {
                        return Mono.error(new FlightNotFoundException(id));
                    }
                });
    }

    @Override
    public Flux<Flight> getByDepartureDestinationAndDate(String departure, String destination, LocalDate date) {
        return flightRepository.findByDepartureAndDestination(departure, destination)
                .flatMap(flight -> flightDetailsRepository.findByFlightIdAndDate(flight.getId(), date)
                        .map(flightDetails -> flight));

    }
}
