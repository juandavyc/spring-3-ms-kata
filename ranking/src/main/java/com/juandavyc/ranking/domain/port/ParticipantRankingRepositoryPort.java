package com.juandavyc.ranking.domain.port;

import com.juandavyc.ranking.domain.model.ParticipantRanking;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParticipantRankingRepositoryPort {

    ParticipantRanking save(ParticipantRanking participantRanking);

    Optional<ParticipantRanking> findByParticipantId(UUID participantId);

    List<ParticipantRanking> findTop3ByOrderByFinalScoreDesc();

    void deleteByParticipantId(UUID participantId);

}
