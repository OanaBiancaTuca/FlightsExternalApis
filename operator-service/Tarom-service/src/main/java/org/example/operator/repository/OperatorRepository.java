package org.example.operator.repository;

import org.example.operator.model.Operator;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.nio.channels.FileChannel;

public interface OperatorRepository extends ReactiveMongoRepository<Operator, String> {

   Mono<Operator> findByName(String op);
}
