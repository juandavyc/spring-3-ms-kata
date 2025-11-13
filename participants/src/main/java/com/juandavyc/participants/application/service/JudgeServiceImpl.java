package com.juandavyc.participants.application.service;

import com.juandavyc.participants.application.dto.CreateJudgeCommand;
import com.juandavyc.participants.application.dto.JudgeResponse;
import com.juandavyc.participants.application.dto.UpdateJudgeCommand;
import com.juandavyc.participants.application.mapper.JudgeApplicationMapper;
import com.juandavyc.participants.application.mapper.JudgeApplicationUpdateMapper;
import com.juandavyc.participants.application.usecases.JudgeService;
import com.juandavyc.participants.domain.model.Judge;
import com.juandavyc.participants.domain.port.JudgeRepositoryPort;
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
