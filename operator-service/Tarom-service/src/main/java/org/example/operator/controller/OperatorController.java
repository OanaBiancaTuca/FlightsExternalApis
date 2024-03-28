package org.example.operator.controller;

import jakarta.validation.Valid;
import org.example.operator.dto.OperatorDto;
import org.example.operator.model.Operator;
import org.example.operator.service.OperatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operator")
public class OperatorController {
    private final OperatorService operatorService;

    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @PostMapping("/add")
    public Mono<Operator> addOperator(@Valid @RequestBody OperatorDto dto) {
        return operatorService.saveOperator(dto);
    }
    @GetMapping
    public Flux<Operator> getAllOperators() {
        return operatorService.getAll();
    }
    @PutMapping("/IBAN/{id}")
    public Mono<Operator> updateOperatorIBAN(@PathVariable String id, @RequestBody OperatorDto dto) {
        return operatorService.modifyIban(id, dto);
    }

}
