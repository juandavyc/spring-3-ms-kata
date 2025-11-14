package com.juandavyc.ranking.domain.port;

import com.juandavyc.ranking.domain.model.RankingSnapshot;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RankingSnapshotRepositoryPort {

    RankingSnapshot save(RankingSnapshot snapshot);

    Optional<RankingSnapshot> findById(UUID id);

    List<RankingSnapshot> findAll();

    void deleteById(UUID id);

}
