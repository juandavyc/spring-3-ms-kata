package com.juandavyc.evaluations.domain.port.query;

import com.juandavyc.evaluations.domain.model.Participant;

import java.util.List;
import java.util.UUID;

public interface ParticipantQueryPort {

    Participant getById(UUID id);
    List<Participant> getAll();
    boolean existsById(UUID id);

}
