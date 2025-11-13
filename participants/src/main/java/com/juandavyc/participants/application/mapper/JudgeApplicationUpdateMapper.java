package com.juandavyc.participants.application.mapper;

import com.juandavyc.participants.application.dto.UpdateJudgeCommand;
import com.juandavyc.participants.application.dto.UpdateParticipantCommand;
import com.juandavyc.participants.domain.model.Judge;
import com.juandavyc.participants.domain.model.Participant;
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
