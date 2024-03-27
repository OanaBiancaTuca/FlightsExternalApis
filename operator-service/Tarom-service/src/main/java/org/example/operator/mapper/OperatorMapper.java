package org.example.operator.mapper;

import org.example.operator.dto.OperatorDto;
import org.example.operator.model.Operator;
import org.springframework.stereotype.Component;

@Component
public class OperatorMapper {
    public OperatorDto entityToDto(Operator entity) {
        if (entity == null) {
            return null;
        }
        OperatorDto dto = new OperatorDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setIBAN(entity.getIBAN());
        dto.setCountry(entity.getCountry());
        return dto;
    }

    public Operator dtoToEntity(OperatorDto dto) {
        if (dto == null) {
            return null;
        }
        Operator operator = new Operator();
        operator.setId(dto.getId());
        operator.setName(dto.getName());
        operator.setCode(dto.getCode());
        operator.setIBAN(dto.getIBAN());
        operator.setCountry(dto.getCountry());
        return operator;
    }
}
