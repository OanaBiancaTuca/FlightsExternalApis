package org.example.config;

import org.example.flight.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class FlightDataInitializer implements ApplicationRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] operators = {"661cdb04f798cc00f6f68b73", "661cdb16f798cc00f6f68b74"};
        String departure = "New York";
        String destination = "London";

        long count = mongoTemplate.count(new Query(), Flight.class);

        if (count == 0) {
            LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
            LocalDate endDate = LocalDate.of(LocalDate.now().getYear(), 12, 31);
            LocalDate currentDate = startDate;

            while (!currentDate.isAfter(endDate)) {
                for (String operatorId : operators) {
                    Flight flight = new Flight();
                    flight.setOperatorId(operatorId);
                    flight.setDeparture(departure);
                    flight.setDestination(destination);
                    flight.setNumberOfSeats(100);
                    flight.setDate(currentDate);
                    flight.setDepartureTime(LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), currentDate.getDayOfMonth(), 8, 0));
                    flight.setArrivalTime(LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), currentDate.getDayOfMonth(), 12, 0));

                    mongoTemplate.save(flight);
                }

                currentDate = currentDate.plusDays(1);
            }
        }
    }
}