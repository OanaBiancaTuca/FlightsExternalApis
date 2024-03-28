package org.example.flight.dto;

import org.example.flight_details.dto.FlightDetailsDto;

public class FlightResponseDto {
    private FlightDto flightDto;
    private FlightDetailsDto flightDetailsDto;

    public FlightResponseDto(FlightDto flightDto, FlightDetailsDto flightDetailsDto) {
        this.flightDto = flightDto;
        this.flightDetailsDto = flightDetailsDto;
    }

    public FlightDto getFlightDto() {
        return flightDto;
    }

    public void setFlightDto(FlightDto flightDto) {
        this.flightDto = flightDto;
    }

    public FlightDetailsDto getFlightDetailsDto() {
        return flightDetailsDto;
    }

    public void setFlightDetailsDto(FlightDetailsDto flightDetailsDto) {
        this.flightDetailsDto = flightDetailsDto;
    }
}
