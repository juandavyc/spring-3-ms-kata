package com.juandavyc.evaluations.infrastructure.rest.mapper;

import com.juandavyc.evaluations.application.dto.CreateEvaluationCommand;
import com.juandavyc.evaluations.application.dto.EvaluationResponse;
import com.juandavyc.evaluations.application.dto.UpdateEvaluationCommand;
import com.juandavyc.evaluations.infrastructure.rest.dto.EvaluationRestRequest;
import com.juandavyc.evaluations.infrastructure.rest.dto.EvaluationRestResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvaluationWebMapper {

    CreateEvaluationCommand toCreateCommand(EvaluationRestRequest request);

    UpdateEvaluationCommand toUpdateCommand(EvaluationRestRequest request);

    EvaluationRestResponse toRestResponse(EvaluationResponse response);

}
