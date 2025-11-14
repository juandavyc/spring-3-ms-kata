package com.juandavyc.ranking.application.mapper;

import com.juandavyc.ranking.application.dto.CreateSnapshotCommand;
import com.juandavyc.ranking.application.dto.RankingSnapshotResponse;
import com.juandavyc.ranking.domain.model.RankingSnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RankingSnapshotApplicationMapper {

    @Mapping(target = "id", ignore = true)
    RankingSnapshot toDomain(CreateSnapshotCommand command);

    RankingSnapshotResponse toResponse(RankingSnapshot domain);

}
