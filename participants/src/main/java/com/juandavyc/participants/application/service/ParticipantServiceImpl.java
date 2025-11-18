package com.juandavyc.participants.application.service;

import com.juandavyc.participants.application.dto.CreateParticipantCommand;
import com.juandavyc.participants.application.dto.ParticipantResponse;
import com.juandavyc.participants.application.dto.UpdateParticipantCommand;
import com.juandavyc.participants.application.mapper.ParticipantApplicationMapper;
import com.juandavyc.participants.application.mapper.ParticipantApplicationUpdateMapper;
import com.juandavyc.participants.application.usecases.ParticipantService;
import com.juandavyc.participants.domain.model.Participant;
import com.juandavyc.participants.domain.port.ParticipantRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepositoryPort repository;
    private final ParticipantApplicationMapper mapper;
    private final ParticipantApplicationUpdateMapper updateMapper;

    @Override
    public ParticipantResponse create(CreateParticipantCommand command) {
        Participant participant = mapper.toParticipant(command);
        participant.setCreatedAt(LocalDateTime.now());
        Participant saved = repository.save(participant);
        return mapper.toParticipantResponse(saved);
    }

    @Override
    public List<ParticipantResponse> getAll() {
        List<Participant> participants = repository.findAll();
        return participants.stream()
                .map(mapper::toParticipantResponse).
                collect(Collectors.toList());
    }

    @Override
    public ParticipantResponse getById(UUID id) {
        Participant participant = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        return mapper.toParticipantResponse(participant);
    }

    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public ParticipantResponse update(UUID id, UpdateParticipantCommand command) {
        Participant participant = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        updateMapper.updateFromCommand(command, participant);
        Participant updated = repository.save(participant);
        return mapper.toParticipantResponse(updated);
    }

    @Override
    public void delete(UUID id) {
        Participant participant = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        repository.deleteById(participant.getId());
    }

}
