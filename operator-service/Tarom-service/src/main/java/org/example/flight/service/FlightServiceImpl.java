package org.example.flight.service;

import org.example.flight.dto.FlightDto;
import org.example.flight.dto.FlightResponseDto;
import org.example.flight.exception.FlightNotFoundException;
import org.example.flight.mapper.FlightMapper;
import org.example.flight.model.Flight;
import org.example.flight.repository.FlightRepository;
import org.example.flight_details.dto.FlightDetailsDto;
import org.example.flight_details.mapper.FlightDetailsMapper;
import org.example.flight_details.repository.FlightDetailsRepository;
import org.example.operator.dto.OperatorDto;
import org.example.operator.exception.OperatorNotFoundException;
import org.example.operator.mapper.OperatorMapper;
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
    private final FlightDetailsMapper flightDetailsMapper;
    private final FlightDetailsRepository flightDetailsRepository;
    private final OperatorMapper operatorMapper;

    public FlightServiceImpl(FlightRepository flightRepository, OperatorRepository operatorRepository, FlightMapper flightMapper,
                             FlightDetailsMapper flightDetailsMapper, FlightDetailsRepository flightDetailsRepository, OperatorMapper operatorMapper) {
        this.flightRepository = flightRepository;
        this.operatorRepository = operatorRepository;
        this.flightMapper = flightMapper;
        this.flightDetailsMapper = flightDetailsMapper;
        this.flightDetailsRepository = flightDetailsRepository;
        this.operatorMapper = operatorMapper;
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
        return operatorRepository.existsById(flightDTO.getOperatorId())
                .flatMap(operatorExists -> {
                    if (operatorExists) {
                        Flight flight = flightMapper.dtoToEntity(flightDTO);
                        return flightRepository.save(flight);
                    } else {
                        return Mono.error(new OperatorNotFoundException(flightDTO.getOperatorId()));
                    }
                });
    }

    @Override
    public Mono<Flight> updateFlight(FlightDto flightDTO) {
        return operatorRepository.existsById(flightDTO.getOperatorId())
                .flatMap(operatorExists -> {
                    if (operatorExists) {
                        return flightRepository.findById(flightDTO.getId())
                                .flatMap(existingFlight -> {
                                    existingFlight.setDeparture(flightDTO.getDeparture());
                                    existingFlight.setDestination(flightDTO.getDestination());
                                    existingFlight.setOperatorId(flightDTO.getOperatorId());
                                    return flightRepository.save(existingFlight);
                                })
                                .switchIfEmpty(Mono.error(new FlightNotFoundException(flightDTO.getId())));
                    } else {
                        return Mono.error(new OperatorNotFoundException(flightDTO.getOperatorId()));
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
    public Flux<FlightResponseDto> getByDepartureDestinationAndDate(String departure, String destination, LocalDate date) {
        return flightRepository.findByDepartureAndDestination(departure, destination)
                .flatMap(flight ->
                        flightDetailsRepository.findByFlightIdAndDate(flight.getId(), date)
                                .flatMap(flightDetails ->
                                        operatorRepository.findById(flight.getOperatorId())
                                                .map(operator -> {
                                                    FlightDto flightDto = flightMapper.entityToDto(flight);
                                                    FlightDetailsDto flightDetailsDto = flightDetailsMapper.entityToDto(flightDetails);
                                                    OperatorDto operatorDto = operatorMapper.entityToDto(operator);
                                                    return new FlightResponseDto(flightDto, flightDetailsDto, operatorDto);
                                                })
                                )
                );
    }
}
