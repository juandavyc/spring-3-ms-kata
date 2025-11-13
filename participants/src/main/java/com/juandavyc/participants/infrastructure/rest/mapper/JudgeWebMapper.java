package com.juandavyc.participants.infrastructure.rest.mapper;

import com.juandavyc.participants.application.dto.*;
import com.juandavyc.participants.infrastructure.rest.dto.JudgeRestRequest;
import com.juandavyc.participants.infrastructure.rest.dto.JudgeRestResponse;
import com.juandavyc.participants.infrastructure.rest.dto.ParticipantRestRequest;
import com.juandavyc.participants.infrastructure.rest.dto.ParticipantRestResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface JudgeWebMapper {

    CreateJudgeCommand toCommand(JudgeRestRequest request);

    UpdateJudgeCommand toUpdateCommand(JudgeRestRequest request);

    JudgeRestResponse toResponseDto(JudgeResponse applicationResponse);

}
