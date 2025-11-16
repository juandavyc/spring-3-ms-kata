package com.juandavyc.evaluations.infrastructure.adapter.feign.query;

import com.juandavyc.evaluations.domain.model.Participant;
import com.juandavyc.evaluations.domain.port.query.ParticipantQueryPort;
import com.juandavyc.evaluations.infrastructure.feign.ParticipantFeignClient;
import com.juandavyc.evaluations.infrastructure.feign.dto.ParticipantRestResponse;
import com.juandavyc.evaluations.infrastructure.feign.mapper.ParticipantWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ParticipantQueryAdapter implements ParticipantQueryPort {

    private final ParticipantFeignClient feignClient;
    private final ParticipantWebMapper mapper;

    @Override
    public Participant getById(UUID id) {
        ParticipantRestResponse response = feignClient.getById(id);
        return mapper.toDomain(response);
    }

    @Override
    public boolean existsById(UUID id) {
        return feignClient.existsById(id);
    }
}
