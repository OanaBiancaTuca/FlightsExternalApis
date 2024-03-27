package org.example.operator.service;

import org.example.operator.dto.OperatorDto;
import org.example.operator.model.Operator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OperatorService {
    Mono<Operator> saveOperator(OperatorDto operatorDto);
    Flux<Operator> getAll();
    Mono<Operator> modifyIban(String id, OperatorDto operatorDto);
}
