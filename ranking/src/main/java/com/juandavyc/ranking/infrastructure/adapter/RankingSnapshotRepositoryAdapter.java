package com.juandavyc.ranking.infrastructure.adapter;

import com.juandavyc.ranking.domain.model.RankingSnapshot;
import com.juandavyc.ranking.domain.port.RankingSnapshotRepositoryPort;
import com.juandavyc.ranking.infrastructure.adapter.entity.RankingSnapshotEntity;
import com.juandavyc.ranking.infrastructure.adapter.mapper.RankingSnapshotPersistenceMapper;
import com.juandavyc.ranking.infrastructure.adapter.repository.RankingSnapshotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingSnapshotRepositoryAdapter implements RankingSnapshotRepositoryPort {

    private final RankingSnapshotRepository jpaRepository;
    private final RankingSnapshotPersistenceMapper persistenceMapper;

    @Override
    public RankingSnapshot save(RankingSnapshot snapshot) {
        RankingSnapshotEntity entity = persistenceMapper.toEntity(snapshot);
        RankingSnapshotEntity saved = jpaRepository.save(entity);
        return persistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<RankingSnapshot> findById(UUID id) {
        Optional<RankingSnapshotEntity> opt = jpaRepository.findById(id);
        return opt.map(persistenceMapper::toDomain);
    }

    @Override
    public List<RankingSnapshot> findAll() {
        List<RankingSnapshotEntity> list = jpaRepository.findAll();
        return list.stream().map(persistenceMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
