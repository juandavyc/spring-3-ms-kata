package com.juandavyc.ranking.infrastructure.feign.mapper;


import com.juandavyc.ranking.domain.model.Participant;
import com.juandavyc.ranking.infrastructure.feign.dto.ParticipantRestResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipantWebMapper {

    Participant toDomain(ParticipantRestResponse response);

}