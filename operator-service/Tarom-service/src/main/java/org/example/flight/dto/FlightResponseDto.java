package org.example.flight.dto;

import org.example.flight_details.dto.FlightDetailsDto;
import org.example.operator.dto.OperatorDto;

public class FlightResponseDto {
    private FlightDto flightDto;
    private FlightDetailsDto flightDetailsDto;
    private OperatorDto operatorDto;

    public FlightResponseDto() {

    }

    public FlightResponseDto(FlightDto flightDto, FlightDetailsDto flightDetailsDto) {
        this.flightDto = flightDto;
        this.flightDetailsDto = flightDetailsDto;
    }

    public FlightResponseDto(FlightDto flightDto, FlightDetailsDto flightDetailsDto, OperatorDto operatorDto) {
        this.flightDto = flightDto;
        this.flightDetailsDto = flightDetailsDto;
        this.operatorDto = operatorDto;
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

    public OperatorDto getOperatorDto() {
        return operatorDto;
    }

    public void setOperatorDto(OperatorDto operatorDto) {
        this.operatorDto = operatorDto;
    }

    @Override
    public String toString() {
        return "flight: " + flightDto.getDeparture() + ","
                + flightDto.getDestination() +
                ", details: " + flightDetailsDto.getDate() +
                ", " + flightDetailsDto.getDepartureTime() + ", " +
                flightDetailsDto.getArrivalTime() + ", " +
                flightDetailsDto.getStandardPrice() +
                " and operator: " + operatorDto.getName();
    }
}
