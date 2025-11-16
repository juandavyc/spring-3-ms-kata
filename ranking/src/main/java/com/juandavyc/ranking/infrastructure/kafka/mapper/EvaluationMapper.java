package com.juandavyc.ranking.infrastructure.kafka.mapper;

import com.juandavyc.ranking.application.dto.EvaluationCommand;
import com.juandavyc.ranking.infrastructure.kafka.dto.EvaluationEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvaluationMapper {

    EvaluationCommand toCommand(EvaluationEvent event);

}
