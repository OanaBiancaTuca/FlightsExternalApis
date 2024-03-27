package org.example.flight.dto;

import jakarta.validation.constraints.NotNull;

public class FlightDto {

    private String id;
    @NotNull(message = "Id for Operator cannot be null")
    private String idOperator;
    @NotNull(message = "Departure cannot be null")
    private String departure;
    @NotNull(message = "Destination cannot be null")
    private String destination;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOperator() {
        return idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
