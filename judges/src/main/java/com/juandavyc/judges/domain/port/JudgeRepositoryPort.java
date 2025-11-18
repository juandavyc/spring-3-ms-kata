package com.juandavyc.judges.domain.port;

import com.juandavyc.judges.domain.model.Judge;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JudgeRepositoryPort {

    Judge save(Judge judge);

    Optional<Judge> findById(UUID id);

    boolean existsById(UUID id);

    boolean existsByEmail(String email);

    List<Judge> findAllByOrderByCreatedAtDesc();

    void deleteById(UUID id);
}
