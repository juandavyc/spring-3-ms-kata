package com.juandavyc.participants.application.mapper;

import com.juandavyc.participants.application.dto.CreateJudgeCommand;
import com.juandavyc.participants.application.dto.JudgeResponse;
import com.juandavyc.participants.domain.model.Judge;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JudgeApplicationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    //@Mapping(target = "updatedAt", ignore = true)
    Judge toJudge(CreateJudgeCommand command);

    JudgeResponse toJudgeResponse(Judge judge);
}
