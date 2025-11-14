package com.juandavyc.judges.application.mapper;

import com.juandavyc.judges.application.dto.UpdateJudgeCommand;
import com.juandavyc.judges.domain.model.Judge;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface JudgeApplicationUpdateMapper {

    void updateFromCommand(UpdateJudgeCommand command, @MappingTarget Judge judge);

}