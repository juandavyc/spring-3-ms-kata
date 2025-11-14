package com.juandavyc.ranking.application.mapper;

import com.juandavyc.ranking.application.dto.ParticipantRankingResponse;
import com.juandavyc.ranking.domain.model.ParticipantRanking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipantRankingApplicationMapper {

    ParticipantRankingResponse toResponse(ParticipantRanking domain);

}
