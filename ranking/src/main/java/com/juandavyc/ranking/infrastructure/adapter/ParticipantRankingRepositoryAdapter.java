package com.juandavyc.ranking.infrastructure.adapter;

import com.juandavyc.ranking.domain.model.ParticipantRanking;
import com.juandavyc.ranking.domain.port.ParticipantRankingRepositoryPort;
import com.juandavyc.ranking.infrastructure.adapter.entity.ParticipantRankingEntity;
import com.juandavyc.ranking.infrastructure.adapter.mapper.ParticipantRankingPersistenceMapper;
import com.juandavyc.ranking.infrastructure.adapter.repository.ParticipantRankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipantRankingRepositoryAdapter implements ParticipantRankingRepositoryPort {

    private final ParticipantRankingRepository jpaRepository;
    private final ParticipantRankingPersistenceMapper persistenceMapper;

    @Override
    public ParticipantRanking save(ParticipantRanking participantRanking) {
        ParticipantRankingEntity entity = persistenceMapper.toEntity(participantRanking);
        ParticipantRankingEntity saved = jpaRepository.save(entity);
        return persistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<ParticipantRanking> findByParticipantId(UUID participantId) {
        Optional<ParticipantRankingEntity> opt = jpaRepository.findById(participantId);
        return opt.map(persistenceMapper::toDomain);
    }

    @Override
    public List<ParticipantRanking> findAllByOrderByFinalScoreDesc() {
        List<ParticipantRankingEntity> list = jpaRepository.findAllByOrderByFinalScoreDesc();
        return list.stream().map(persistenceMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteByParticipantId(UUID participantId) {
        jpaRepository.deleteById(participantId);
    }
}
