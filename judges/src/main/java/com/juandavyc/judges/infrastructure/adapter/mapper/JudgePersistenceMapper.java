package com.juandavyc.judges.infrastructure.adapter.mapper;

import com.juandavyc.judges.domain.model.Judge;
import com.juandavyc.judges.infrastructure.adapter.entity.JudgeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JudgePersistenceMapper {

    JudgeEntity toEntity(Judge domain);
    Judge toDomain(JudgeEntity entity);

}
