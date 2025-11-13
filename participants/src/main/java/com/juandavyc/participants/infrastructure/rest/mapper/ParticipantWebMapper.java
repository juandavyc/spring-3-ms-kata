package com.juandavyc.participants.infrastructure.rest.mapper;

import com.juandavyc.participants.application.dto.CreateParticipantCommand;
import com.juandavyc.participants.application.dto.ParticipantResponse;
import com.juandavyc.participants.application.dto.UpdateParticipantCommand;
import com.juandavyc.participants.infrastructure.rest.dto.ParticipantRestRequest;
import com.juandavyc.participants.infrastructure.rest.dto.ParticipantRestResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipantWebMapper {

    CreateParticipantCommand toCommand(ParticipantRestRequest request);

    UpdateParticipantCommand toUpdateCommand(ParticipantRestRequest request);

    ParticipantRestResponse toResponseDto(ParticipantResponse applicationResponse);

}