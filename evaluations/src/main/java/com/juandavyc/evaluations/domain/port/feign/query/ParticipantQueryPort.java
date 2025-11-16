package com.juandavyc.evaluations.domain.port.feign.query;

import com.juandavyc.evaluations.domain.model.feign.Participant;

import java.util.UUID;

public interface ParticipantQueryPort {

    Participant getById(UUID id);
    boolean existsById(UUID id);

}
