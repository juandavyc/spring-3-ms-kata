package com.juandavyc.ranking.infrastructure.rest.mapper;

import com.juandavyc.ranking.application.dto.ParticipantRankingResponse;
import com.juandavyc.ranking.infrastructure.rest.dto.ParticipantRankingRestResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipantRankingWebMapper {


    ParticipantRankingRestResponse toRest(ParticipantRankingResponse response);

}
