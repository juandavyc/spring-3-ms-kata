package com.juandavyc.judges.application.mapper;

import com.juandavyc.judges.application.dto.CreateJudgeCommand;
import com.juandavyc.judges.application.dto.JudgeResponse;
import com.juandavyc.judges.domain.model.Judge;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JudgeApplicationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Judge toJudge(CreateJudgeCommand command);

    JudgeResponse toJudgeResponse(Judge judge);
}