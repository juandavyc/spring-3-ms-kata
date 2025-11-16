package com.juandavyc.evaluations.infrastructure.feign.mapper;


import com.juandavyc.evaluations.domain.model.Participant;
import com.juandavyc.evaluations.infrastructure.feign.dto.ParticipantRestResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipantWebMapper {

    Participant toDomain(ParticipantRestResponse response);

}
