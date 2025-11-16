package com.juandavyc.ranking.application.mapper;

import com.juandavyc.ranking.application.dto.EvaluationCommand;
import com.juandavyc.ranking.domain.model.Evaluation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvaluationApplicationMapper {

    Evaluation toDomain (EvaluationCommand command);
}
