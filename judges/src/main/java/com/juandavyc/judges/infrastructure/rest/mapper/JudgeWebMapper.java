package com.juandavyc.judges.infrastructure.rest.mapper;

import com.juandavyc.judges.application.dto.*;
import com.juandavyc.judges.infrastructure.rest.dto.JudgeRestRequest;
import com.juandavyc.judges.infrastructure.rest.dto.JudgeRestResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface JudgeWebMapper {

    CreateJudgeCommand toCommand(JudgeRestRequest request);

    UpdateJudgeCommand toUpdateCommand(JudgeRestRequest request);

    JudgeRestResponse toResponseDto(JudgeResponse applicationResponse);

}
