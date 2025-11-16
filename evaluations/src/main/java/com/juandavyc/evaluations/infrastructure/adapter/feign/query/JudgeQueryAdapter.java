package com.juandavyc.evaluations.infrastructure.adapter.feign.query;

import com.juandavyc.evaluations.domain.model.Judge;
import com.juandavyc.evaluations.domain.port.query.JudgeQueryPort;
import com.juandavyc.evaluations.infrastructure.feign.JudgeFeignClient;
import com.juandavyc.evaluations.infrastructure.feign.dto.JudgeRestResponse;
import com.juandavyc.evaluations.infrastructure.feign.mapper.JudgeWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JudgeQueryAdapter implements JudgeQueryPort {

    private final JudgeFeignClient feignClient;
    private final JudgeWebMapper mapper;

    @Override
    public Judge getById(UUID id) {
        JudgeRestResponse response = feignClient.getById(id);
        return mapper.toDomain(response);
    }

    @Override
    public boolean existsById(UUID id) {
        return feignClient.existsById(id);
    }
}
