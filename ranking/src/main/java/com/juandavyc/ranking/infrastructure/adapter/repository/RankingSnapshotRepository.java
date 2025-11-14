package com.juandavyc.ranking.infrastructure.adapter.repository;

import com.juandavyc.ranking.infrastructure.adapter.entity.RankingSnapshotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RankingSnapshotRepository extends JpaRepository<RankingSnapshotEntity, UUID> {

}
