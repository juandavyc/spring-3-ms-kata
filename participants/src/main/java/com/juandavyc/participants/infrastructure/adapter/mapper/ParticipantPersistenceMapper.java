package com.juandavyc.participants.infrastructure.adapter.mapper;


import com.juandavyc.participants.domain.model.Participant;
import com.juandavyc.participants.infrastructure.adapter.entity.ParticipantEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipantPersistenceMapper {

    ParticipantEntity toEntity(Participant domain);
    Participant toDomain(ParticipantEntity entity);

}
