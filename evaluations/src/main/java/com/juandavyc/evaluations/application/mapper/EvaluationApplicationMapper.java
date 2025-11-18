package com.juandavyc.evaluations.application.mapper;

import com.juandavyc.evaluations.application.dto.CreateEvaluationCommand;
import com.juandavyc.evaluations.application.dto.EvaluationResponse;
import com.juandavyc.evaluations.application.dto.ParticipantLastEvaluationResponse;
import com.juandavyc.evaluations.application.dto.ParticipantResponse;
import com.juandavyc.evaluations.domain.model.Evaluation;
import com.juandavyc.evaluations.domain.model.Participant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EvaluationApplicationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalScore", ignore = true)
    @Mapping(target = "approved", ignore = true)
    @Mapping(target = "evaluationDate", ignore = true)
    Evaluation toEvaluation(CreateEvaluationCommand command);

    EvaluationResponse toEvaluationResponse(Evaluation evaluation);

    ParticipantResponse toParticipantResponse(Participant participant);


    default ParticipantLastEvaluationResponse toParticipantLastEvaluationResponse(Participant participant, Evaluation evaluation) {
        ParticipantLastEvaluationResponse dto = new ParticipantLastEvaluationResponse();
        dto.setParticipant(toParticipantResponse(participant));
        dto.setEvaluation(toEvaluationResponse(evaluation));
        return dto;
    }

}
