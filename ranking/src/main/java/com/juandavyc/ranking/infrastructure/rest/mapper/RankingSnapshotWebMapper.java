package com.juandavyc.ranking.infrastructure.rest.mapper;

import com.juandavyc.ranking.application.dto.RankingSnapshotResponse;
import com.juandavyc.ranking.infrastructure.rest.dto.RankingSnapshotRestResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RankingSnapshotWebMapper {

    RankingSnapshotRestResponse toRest(RankingSnapshotResponse response);

}
