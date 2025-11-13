package com.juandavyc.participants.infrastructure.adapter.mapper;

import com.juandavyc.participants.domain.model.Judge;
import com.juandavyc.participants.infrastructure.adapter.entity.JudgeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JudgePersistenceMapper {

    JudgeEntity toEntity(Judge domain);
    Judge toDomain(JudgeEntity entity);

}
