package com.juandavyc.ranking.infrastructure.adapter.mapper;

import com.juandavyc.ranking.domain.model.ParticipantRanking;
import com.juandavyc.ranking.infrastructure.adapter.entity.ParticipantRankingEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipantRankingPersistenceMapper {

    ParticipantRanking toDomain(ParticipantRankingEntity entity);

    ParticipantRankingEntity toEntity(ParticipantRanking domain);

}
