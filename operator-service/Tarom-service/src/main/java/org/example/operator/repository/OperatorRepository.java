package org.example.operator.repository;

import org.example.operator.model.Operator;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OperatorRepository extends ReactiveMongoRepository<Operator, String> {
}