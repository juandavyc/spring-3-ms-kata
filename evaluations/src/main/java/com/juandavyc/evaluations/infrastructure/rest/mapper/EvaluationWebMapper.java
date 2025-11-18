package com.juandavyc.evaluations.infrastructure.rest.mapper;

import com.juandavyc.evaluations.application.dto.CreateEvaluationCommand;
import com.juandavyc.evaluations.application.dto.EvaluationResponse;
import com.juandavyc.evaluations.application.dto.ParticipantLastEvaluationResponse;
import com.juandavyc.evaluations.application.dto.UpdateEvaluationCommand;
import com.juandavyc.evaluations.domain.model.Participant;
import com.juandavyc.evaluations.infrastructure.rest.dto.EvaluationRestRequest;
import com.juandavyc.evaluations.infrastructure.rest.dto.EvaluationRestResponse;
import com.juandavyc.evaluations.infrastructure.rest.dto.ParticipantLastEvaluationRestResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvaluationWebMapper {

    CreateEvaluationCommand toCreateCommand(EvaluationRestRequest request);

    UpdateEvaluationCommand toUpdateCommand(EvaluationRestRequest request);

    EvaluationRestResponse toRestResponse(EvaluationResponse response);

    ParticipantLastEvaluationRestResponse toParticipantLastEvaluationRestResponse(ParticipantLastEvaluationResponse participantLastEvaluationResponse);

}
