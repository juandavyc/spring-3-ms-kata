package com.juandavyc.participants.domain.port;

import com.juandavyc.participants.domain.model.Participant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ParticipantRepositoryPort {

    Participant save(Participant participant);

    Optional<Participant> findById(UUID id);

    boolean existsById(UUID id);

    List<Participant> findAll();

    void deleteById(UUID id);

}
