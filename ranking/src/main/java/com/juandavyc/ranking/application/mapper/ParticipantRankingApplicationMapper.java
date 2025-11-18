package com.juandavyc.ranking.application.mapper;

import com.juandavyc.ranking.application.dto.EvaluationResponse;
import com.juandavyc.ranking.application.dto.ParticipantRankingResponse;
import com.juandavyc.ranking.application.dto.ParticipantResponse;
import com.juandavyc.ranking.domain.model.Evaluation;
import com.juandavyc.ranking.domain.model.Participant;
import com.juandavyc.ranking.domain.model.ParticipantRanking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParticipantRankingApplicationMapper {

   // ParticipantRankingResponse toResponse(ParticipantRanking domain);

    EvaluationResponse toEvaluationResponse(Evaluation evaluation);

    ParticipantResponse toParticipantResponse(Participant participant);

    @Mapping(target = "participant", ignore = true)
    ParticipantRankingResponse toParticipantRankingResponse(ParticipantRanking participantRanking);

}
