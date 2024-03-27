package org.example.flight_details.service;


import org.example.flight.exception.FlightNotFoundException;
import org.example.flight.repository.FlightRepository;
import org.example.flight_details.dto.FlightDetailsDto;
import org.example.flight_details.exception.FlightDetailsNotFoundException;
import org.example.flight_details.mapper.FlightDetailsMapper;
import org.example.flight_details.model.FlightDetails;
import org.example.flight_details.repository.FlightDetailsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FlightDetailsServiceImpl implements FlightDetailsService {
    private final FlightDetailsRepository flightDetailsRepository;
    private final FlightRepository flightRepository;
    private final FlightDetailsMapper flightDetailsMapper;

    public FlightDetailsServiceImpl(FlightDetailsRepository flightDetailsRepository, FlightRepository flightRepository, FlightDetailsMapper flightDetailsMapper) {
        this.flightDetailsRepository = flightDetailsRepository;
        this.flightRepository = flightRepository;
        this.flightDetailsMapper = flightDetailsMapper;
    }

    @Override
    public Flux<FlightDetails> getAllFlightDetails() {
        return flightDetailsRepository.findAll();
    }

    @Override
    public Mono<FlightDetails> getFlightDetailsById(String id) {
        return flightDetailsRepository.findById(id)
                .switchIfEmpty(Mono.error(new FlightDetailsNotFoundException(id)));
    }

    @Override
    public Mono<FlightDetails> createFlightDetails(FlightDetailsDto flightDetailsDTO) {
        FlightDetails flightDetails = flightDetailsMapper.dtoToEntity(flightDetailsDTO);
        return flightRepository.existsById(flightDetails.getFlightId())
                .flatMap(exists -> {
                    if (exists) {
                        return flightDetailsRepository.save(flightDetails);
                    } else {
                        return Mono.error(new FlightNotFoundException(flightDetails.getFlightId()));
                    }
                });
    }

    @Override
    public Mono<FlightDetails> updateFlightDetails(FlightDetailsDto flightDetailsDTO) {
        return flightRepository.existsById(flightDetailsDTO.getFlightId())
                .flatMap(exists -> {
                    if (exists) {
                        return flightDetailsRepository.findById(flightDetailsDTO.getId())
                                .flatMap(existingFlightDetails -> {
                                    existingFlightDetails.setNumberOfSeats(flightDetailsDTO.getNumberOfSeats());
                                    existingFlightDetails.setDate(flightDetailsDTO.getDate());
                                    existingFlightDetails.setFlightId(flightDetailsDTO.getFlightId());
                                    return flightDetailsRepository.save(existingFlightDetails);
                                })
                                .switchIfEmpty(Mono.error(new FlightDetailsNotFoundException(flightDetailsDTO.getId())));
                    } else {
                        return Mono.error(new FlightNotFoundException(flightDetailsDTO.getFlightId()));
                    }
                });
    }

    @Override
    public Mono<Void> deleteFlightDetails(String id) {
        return flightDetailsRepository.existsById(id)
                .flatMap(exists -> {
                    if (exists) {
                        return flightDetailsRepository.deleteById(id);
                    } else {
                        return Mono.error(new FlightDetailsNotFoundException(id));
                    }
                });
    }

    @Override
    public Flux<FlightDetails> getByFlightId(String flightId) {
        return flightDetailsRepository.findByFlightId(flightId);
    }
}
