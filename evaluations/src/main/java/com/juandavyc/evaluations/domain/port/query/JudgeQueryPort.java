package com.juandavyc.evaluations.domain.port.query;


import com.juandavyc.evaluations.domain.model.Judge;

import java.util.UUID;

public interface JudgeQueryPort {

    Judge getById(UUID id);
    boolean existsById(UUID id);

}
