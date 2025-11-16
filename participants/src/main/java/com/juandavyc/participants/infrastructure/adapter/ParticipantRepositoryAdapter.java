package com.juandavyc.participants.infrastructure.adapter;

import com.juandavyc.participants.domain.model.Participant;
import com.juandavyc.participants.domain.port.ParticipantRepositoryPort;
import com.juandavyc.participants.infrastructure.adapter.entity.ParticipantEntity;
import com.juandavyc.participants.infrastructure.adapter.mapper.ParticipantPersistenceMapper;
import com.juandavyc.participants.infrastructure.adapter.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipantRepositoryAdapter implements ParticipantRepositoryPort {

    private final ParticipantRepository jpaRepository;
    private final ParticipantPersistenceMapper persistenceMapper;

    @Override
    public Participant save(Participant domain) {
        ParticipantEntity entity = persistenceMapper.toEntity(domain);
        ParticipantEntity saved = jpaRepository.save(entity);
        return persistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Participant> findById(UUID id) {
        Optional<ParticipantEntity> optionalParticipantEntity = jpaRepository.findById(id);
        return optionalParticipantEntity.map(persistenceMapper::toDomain);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public List<Participant> findAll() {
        List<ParticipantEntity> entities = jpaRepository.findAll();
        return entities.stream()
                .map(persistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
