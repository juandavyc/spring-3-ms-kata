package com.juandavyc.evaluations.domain.port;

import com.juandavyc.evaluations.domain.model.Evaluation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EvaluationRepositoryPort {

    Evaluation save(Evaluation evaluation);
    Optional<Evaluation> findById(UUID id);
    List<Evaluation> findAll();
    void delete(UUID id);

}
