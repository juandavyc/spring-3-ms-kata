package com.juandavyc.participants.application.usecases;


import com.juandavyc.participants.application.dto.CreateParticipantCommand;
import com.juandavyc.participants.application.dto.ParticipantResponse;
import com.juandavyc.participants.application.dto.UpdateParticipantCommand;
import com.juandavyc.participants.domain.model.Participant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface ParticipantService {

    ParticipantResponse create(CreateParticipantCommand command);
    List<ParticipantResponse> getAll();
    ParticipantResponse getById(UUID id);
    boolean existsById(UUID id);
    ParticipantResponse update(UUID id, UpdateParticipantCommand command);
    void delete(UUID id);

}
