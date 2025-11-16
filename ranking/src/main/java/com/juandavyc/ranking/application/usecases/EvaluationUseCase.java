package com.juandavyc.ranking.application.usecases;

import com.juandavyc.ranking.application.dto.EvaluationCommand;
import com.juandavyc.ranking.infrastructure.kafka.dto.EvaluationEvent;

public interface EvaluationUseCase {
    void processEvaluation(EvaluationCommand command);
}
