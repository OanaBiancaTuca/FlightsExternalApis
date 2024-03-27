package org.example.flight.service;

import org.example.flight.dto.FlightDto;
import org.example.flight.exception.FlightNotFoundException;
import org.example.flight.mapper.FlightMapper;
import org.example.flight.model.Flight;
import org.example.flight.repository.FlightRepository;
import org.example.operator.exception.OperatorNotFoundException;
import org.example.operator.repository.OperatorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final OperatorRepository operatorRepository;
    private final FlightMapper flightMapper;

    public FlightServiceImpl(FlightRepository flightRepository, OperatorRepository operatorRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.operatorRepository = operatorRepository;
        this.flightMapper = flightMapper;
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
}
