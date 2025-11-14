package com.juandavyc.judges.application.service;

import com.juandavyc.judges.application.dto.CreateJudgeCommand;
import com.juandavyc.judges.application.dto.JudgeResponse;
import com.juandavyc.judges.application.dto.UpdateJudgeCommand;
import com.juandavyc.judges.application.mapper.JudgeApplicationMapper;
import com.juandavyc.judges.application.mapper.JudgeApplicationUpdateMapper;
import com.juandavyc.judges.application.usecases.JudgeService;
import com.juandavyc.judges.domain.model.Judge;
import com.juandavyc.judges.domain.port.JudgeRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JudgeServiceImpl implements JudgeService {

    private final JudgeRepositoryPort repository;
    private final JudgeApplicationMapper mapper;
    private final JudgeApplicationUpdateMapper updateMapper;

    @Override
    public JudgeResponse create(CreateJudgeCommand command) {
        Judge judge = mapper.toJudge(command);
        Judge saved = repository.save(judge);
        return mapper.toJudgeResponse(saved);
    }

    @Override
    public List<JudgeResponse> getALl() {
        List<Judge> judges = repository.findAll();
        return judges.stream()
                .map(mapper::toJudgeResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JudgeResponse getById(UUID id) {
        Judge judge = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        return mapper.toJudgeResponse(judge);
    }

    @Override
    public JudgeResponse update(UUID id, UpdateJudgeCommand command) {
        Judge judge = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        updateMapper.updateFromCommand(command, judge);
        Judge saved = repository.save(judge);
        return mapper.toJudgeResponse(saved);
    }

    @Override
    public void delete(UUID id) {
        Judge judge = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        repository.deleteById(judge.getId());

    }
}