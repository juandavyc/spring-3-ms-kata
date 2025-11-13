package com.juandavyc.participants.application.usecases;


import com.juandavyc.participants.application.dto.CreateParticipantCommand;
import com.juandavyc.participants.application.dto.ParticipantResponse;
import com.juandavyc.participants.application.dto.UpdateParticipantCommand;
import com.juandavyc.participants.domain.model.Judge;
import com.juandavyc.participants.domain.model.Participant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface ParticipantService {

    ParticipantResponse createParticipant(CreateParticipantCommand command);
    List<ParticipantResponse> getAllParticipants();
    ParticipantResponse getParticipantById(UUID id);
    ParticipantResponse updateParticipant(UUID id, UpdateParticipantCommand command);
    void deleteParticipant(UUID id);

}
