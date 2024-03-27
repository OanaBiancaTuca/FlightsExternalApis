package org.example.flight_details.mapper;

import org.example.flight_details.dto.FlightDetailsDto;
import org.example.flight_details.model.FlightDetails;
import org.springframework.stereotype.Component;

@Component
public class FlightDetailsMapper {

    public FlightDetails dtoToEntity(FlightDetailsDto dto) {
        if (dto == null) {
            return null;
        }
        FlightDetails entity = new FlightDetails();

        entity.setId(dto.getId());
        entity.setFlightId(dto.getFlightId());
        entity.setDate(dto.getDate());
        entity.setDepartureTime(dto.getDepartureTime());
        entity.setArrivalTime(dto.getArrivalTime());
        entity.setNumberOfSeats(dto.getNumberOfSeats());
        entity.setStandardPrice(dto.getStandardPrice());

        return entity;
    }

    public FlightDetailsDto entityToDto(FlightDetails entity) {
        if (entity == null) {
            return null;
        }
        FlightDetailsDto dto = new FlightDetailsDto();

        dto.setId(entity.getId());
        dto.setFlightId(entity.getFlightId());
        dto.setDate(entity.getDate());
        dto.setDepartureTime(entity.getDepartureTime());
        dto.setArrivalTime(entity.getArrivalTime());
        dto.setNumberOfSeats(entity.getNumberOfSeats());
        dto.setStandardPrice(entity.getStandardPrice());

        return dto;
    }
}
