package org.example.flight.dto;

import org.example.operator.dto.OperatorDto;

public class FlightResponseDto {
    private FlightDto flightDto;
    private OperatorDto operatorDto;

    public FlightResponseDto() {

    }

    public FlightResponseDto(FlightDto flightDto) {
        this.flightDto = flightDto;
    }

    public FlightResponseDto(FlightDto flightDto, OperatorDto operatorDto) {
        this.flightDto = flightDto;
        this.operatorDto = operatorDto;
    }

    public FlightDto getFlightDto() {
        return flightDto;
    }

    public void setFlightDto(FlightDto flightDto) {
        this.flightDto = flightDto;
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
                ", details: " + flightDto.getDate() +
                ", " + flightDto.getDepartureTime() + ", " +
                flightDto.getArrivalTime() + ", " +
                flightDto.getStandardPrice() +
                " and operator: " + operatorDto.getName();
    }
}
