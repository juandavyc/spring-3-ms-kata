package com.juandavyc.ranking.infrastructure.adapter.repository;

import com.juandavyc.ranking.infrastructure.adapter.entity.ParticipantRankingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ParticipantRankingRepository extends JpaRepository<ParticipantRankingEntity, UUID> {
    List<ParticipantRankingEntity> findAllByOrderByFinalScoreDesc();
}
