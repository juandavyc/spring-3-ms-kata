package com.juandavyc.evaluations.domain.port.event;

import com.juandavyc.evaluations.domain.model.Evaluation;

public interface EvaluationEventPublisherPort {
    void publishEvaluationCreated(Evaluation evaluation);
}
