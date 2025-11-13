package com.juandavyc.participants.application.mapper;

import com.juandavyc.participants.application.dto.CreateParticipantCommand;
import com.juandavyc.participants.application.dto.ParticipantResponse;
import com.juandavyc.participants.domain.model.Participant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParticipantApplicationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Participant toParticipant(CreateParticipantCommand command);

    ParticipantResponse toParticipantResponse(Participant participant);

}
