package com.juandavyc.evaluations.domain.port.feign.query;


import com.juandavyc.evaluations.domain.model.feign.Judge;

import java.util.UUID;

public interface JudgeQueryPort {

    Judge getById(UUID id);
    boolean existsById(UUID id);

}
