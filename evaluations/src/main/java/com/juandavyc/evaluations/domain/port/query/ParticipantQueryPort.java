package com.juandavyc.evaluations.domain.port.query;

import com.juandavyc.evaluations.domain.model.Participant;

import java.util.UUID;

public interface ParticipantQueryPort {

    Participant getById(UUID id);
    boolean existsById(UUID id);

}
