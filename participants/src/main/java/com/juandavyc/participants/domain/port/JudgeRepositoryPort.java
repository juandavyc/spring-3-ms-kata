package com.juandavyc.participants.domain.port;

import com.juandavyc.participants.domain.model.Judge;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JudgeRepositoryPort {

    Judge save(Judge judge);

    Optional<Judge> findById(UUID id);

    List<Judge> findAll();

    void deleteById(UUID id);
}
