package com.juandavyc.ranking.infrastructure.adapter.feign.query;

import com.juandavyc.ranking.domain.model.Participant;
import com.juandavyc.ranking.domain.port.query.ParticipantQueryPort;
import com.juandavyc.ranking.infrastructure.feign.ParticipantFeignClient;
import com.juandavyc.ranking.infrastructure.feign.dto.ParticipantRestResponse;
import com.juandavyc.ranking.infrastructure.feign.mapper.ParticipantWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public List<Participant> getAll() {
        List<ParticipantRestResponse> response = feignClient.getAll();

        return response.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(UUID id) {
        return feignClient.existsById(id);
    }
}