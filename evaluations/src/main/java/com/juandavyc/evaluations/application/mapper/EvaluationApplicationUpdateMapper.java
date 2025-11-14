package com.juandavyc.evaluations.application.mapper;

import com.juandavyc.evaluations.application.dto.UpdateEvaluationCommand;
import com.juandavyc.evaluations.domain.model.Evaluation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EvaluationApplicationUpdateMapper {

    void updateFromCommand(UpdateEvaluationCommand command, @MappingTarget Evaluation evaluation);

}
