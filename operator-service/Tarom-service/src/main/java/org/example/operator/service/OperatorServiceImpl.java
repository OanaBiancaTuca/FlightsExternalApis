package org.example.operator.service;

import org.example.operator.dto.OperatorDto;
import org.example.operator.exception.OperatorNotFoundException;
import org.example.operator.mapper.OperatorMapper;
import org.example.operator.model.Operator;
import org.example.operator.repository.OperatorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OperatorServiceImpl implements OperatorService {
    private final OperatorRepository operatorRepository;
    private final OperatorMapper operatorMapper;


    public OperatorServiceImpl(OperatorRepository operatorRepository, OperatorMapper operatorMapper) {
        this.operatorRepository = operatorRepository;
        this.operatorMapper = operatorMapper;
    }

    @Override
    public Mono<Operator> saveOperator(OperatorDto dto) {
        Operator operator = operatorMapper.dtoToEntity(dto);
        return operatorRepository.save(operator);
    }

    @Override
    public Flux<Operator> getAll() {
        return operatorRepository.findAll().switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<Operator> modifyIban(String id, OperatorDto dto) {
        return operatorRepository.findById(id)
                .flatMap(operator -> {
                    operator.setIBAN(dto.getIBAN());
                    return operatorRepository.save(operator);
                })
                .switchIfEmpty(Mono.error(new OperatorNotFoundException(id)));
    }
}

