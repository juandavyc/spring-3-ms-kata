package com.juandavyc.ranking.infrastructure.adapter.mapper;

import com.juandavyc.ranking.domain.model.RankingSnapshot;
import com.juandavyc.ranking.infrastructure.adapter.entity.RankingSnapshotEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RankingSnapshotPersistenceMapper {

    RankingSnapshot toDomain(RankingSnapshotEntity entity);

    RankingSnapshotEntity toEntity(RankingSnapshot domain);

}
