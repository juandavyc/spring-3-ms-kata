package com.juandavyc.evaluations.application.usecases;

import com.juandavyc.evaluations.application.dto.CreateEvaluationCommand;
import com.juandavyc.evaluations.application.dto.EvaluationResponse;
import com.juandavyc.evaluations.application.dto.UpdateEvaluationCommand;

import java.util.List;
import java.util.UUID;

public interface EvaluationUseCase {

    EvaluationResponse createEvaluation(CreateEvaluationCommand command);

    EvaluationResponse getEvaluationById(UUID id);

    List<EvaluationResponse> getAllEvaluations();

    EvaluationResponse updateEvaluation(UUID id, UpdateEvaluationCommand command);

    void deleteEvaluation(UUID id);

}
