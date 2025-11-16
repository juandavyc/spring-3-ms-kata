package com.juandavyc.evaluations.infrastructure.feign.mapper;

import com.juandavyc.evaluations.domain.model.Judge;
import com.juandavyc.evaluations.infrastructure.feign.dto.JudgeRestResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JudgeWebMapper {

    Judge toDomain(JudgeRestResponse response);


}
