package com.juandavyc.evaluations.infrastructure.adapter.mapper;

import com.juandavyc.evaluations.domain.model.Evaluation;
import com.juandavyc.evaluations.infrastructure.adapter.entity.EvaluationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvaluationPersistenceMapper {

    EvaluationEntity toEntity(Evaluation domain);

    Evaluation toDomain(EvaluationEntity entity);

}
