package com.juandavyc.judges.infrastructure.adapter;

import com.juandavyc.judges.domain.model.Judge;
import com.juandavyc.judges.domain.port.JudgeRepositoryPort;
import com.juandavyc.judges.infrastructure.adapter.entity.JudgeEntity;
import com.juandavyc.judges.infrastructure.adapter.mapper.JudgePersistenceMapper;
import com.juandavyc.judges.infrastructure.adapter.repository.JudgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JudgeRepositoryAdapter implements JudgeRepositoryPort {

    private final JudgeRepository jpaRepository;
    private final JudgePersistenceMapper persistenceMapper;

    @Override
    public Judge save(Judge judge) {
        JudgeEntity judgeEntity = persistenceMapper.toEntity(judge);
        JudgeEntity savedEntity = jpaRepository.save(judgeEntity);
        return persistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Judge> findById(UUID id) {
        Optional<JudgeEntity> optionalJudgeEntity = jpaRepository.findById(id);
        return optionalJudgeEntity.map(persistenceMapper::toDomain);
    }

    @Override
    public List<Judge> findAll() {
        List<JudgeEntity> entities = jpaRepository.findAll();
        return entities.stream()
                .map(persistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}