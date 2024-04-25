package org.example.config;

import org.example.flight.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Component;

@Component
public class CreateMongoIndexes implements ApplicationRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        mongoTemplate.indexOps(Flight.class).ensureIndex(new Index().on("departure", Sort.Direction.ASC));
        mongoTemplate.indexOps(Flight.class).ensureIndex(new Index().on("destination", Sort.Direction.ASC));
        mongoTemplate.indexOps(Flight.class).ensureIndex(new Index().on("date", Sort.Direction.ASC));
        mongoTemplate.indexOps(Flight.class).ensureIndex(new Index().on("numberOfSeats", Sort.Direction.ASC));
    }
}