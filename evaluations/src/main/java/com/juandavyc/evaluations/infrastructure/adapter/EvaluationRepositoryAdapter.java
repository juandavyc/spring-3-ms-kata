package com.juandavyc.evaluations.infrastructure.adapter;

import com.juandavyc.evaluations.domain.model.Evaluation;
import com.juandavyc.evaluations.domain.port.EvaluationRepositoryPort;
import com.juandavyc.evaluations.infrastructure.adapter.entity.EvaluationEntity;
import com.juandavyc.evaluations.infrastructure.adapter.mapper.EvaluationPersistenceMapper;
import com.juandavyc.evaluations.infrastructure.adapter.repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvaluationRepositoryAdapter implements EvaluationRepositoryPort {

    private final EvaluationRepository jpaRepository;
    private final EvaluationPersistenceMapper persistenceMapper;

    @Override
    public Evaluation save(Evaluation domain) {
        EvaluationEntity entity = persistenceMapper.toEntity(domain);
        EvaluationEntity saved = jpaRepository.save(entity);
        return persistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Evaluation> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(persistenceMapper::toDomain);
    }

    @Override
    public List<Evaluation> findAll() {
        return jpaRepository.findAll().stream()
                .map(persistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }

}
