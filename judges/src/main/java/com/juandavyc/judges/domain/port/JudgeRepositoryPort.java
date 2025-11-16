package com.juandavyc.judges.domain.port;

import com.juandavyc.judges.domain.model.Judge;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JudgeRepositoryPort {

    Judge save(Judge judge);

    Optional<Judge> findById(UUID id);

    boolean existsById(UUID id);

    List<Judge> findAll();

    void deleteById(UUID id);
}
