package com.juandavyc.ranking.domain.service;

import com.juandavyc.ranking.domain.model.kafka.Evaluation;

public interface EvaluationProcessor {
    void processEvaluation(Evaluation event);
}
