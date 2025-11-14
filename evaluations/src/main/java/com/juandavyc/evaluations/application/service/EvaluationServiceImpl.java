package com.juandavyc.evaluations.application.service;

import com.juandavyc.evaluations.application.dto.CreateEvaluationCommand;
import com.juandavyc.evaluations.application.dto.EvaluationResponse;
import com.juandavyc.evaluations.application.dto.UpdateEvaluationCommand;
import com.juandavyc.evaluations.application.exceptions.EvaluationNotFoundException;
import com.juandavyc.evaluations.application.mapper.EvaluationApplicationMapper;
import com.juandavyc.evaluations.application.mapper.EvaluationApplicationUpdateMapper;
import com.juandavyc.evaluations.application.usecases.EvaluationService;
import com.juandavyc.evaluations.domain.model.Evaluation;
import com.juandavyc.evaluations.domain.port.EvaluationRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EvaluationServiceImpl implements EvaluationService {

    private final EvaluationRepositoryPort evaluationRepositoryPort;
    private final EvaluationApplicationMapper applicationMapper;
    private final EvaluationApplicationUpdateMapper updateMapper;

    @Override
    public EvaluationResponse createEvaluation(CreateEvaluationCommand command) {
        Evaluation evaluation = applicationMapper.toEvaluation(command);
        
        // Calculate total score
        BigDecimal totalScore = command.profileScore()
                .add(command.communicationScore())
                .add(command.technicalScore())
                .add(command.extraPoints());
        
        evaluation.setTotalScore(totalScore);
        evaluation.setApproved(totalScore.compareTo(new BigDecimal("70")) >= 0);
        evaluation.setEvaluationDate(LocalDateTime.now());
        
        Evaluation saved = evaluationRepositoryPort.save(evaluation);
        return applicationMapper.toEvaluationResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public EvaluationResponse getEvaluationById(UUID id) {
        Evaluation evaluation = evaluationRepositoryPort.findById(id)
                .orElseThrow(() -> new EvaluationNotFoundException("Evaluation not found with id: " + id));
        return applicationMapper.toEvaluationResponse(evaluation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EvaluationResponse> getAllEvaluations() {
        return evaluationRepositoryPort.findAll().stream()
                .map(applicationMapper::toEvaluationResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EvaluationResponse updateEvaluation(UUID id, UpdateEvaluationCommand command) {
        Evaluation evaluation = evaluationRepositoryPort.findById(id)
                .orElseThrow(() -> new EvaluationNotFoundException("Evaluation not found with id: " + id));
        
        updateMapper.updateFromCommand(command, evaluation);
        
        // Recalculate total score
        BigDecimal totalScore = evaluation.getProfileScore()
                .add(evaluation.getCommunicationScore())
                .add(evaluation.getTechnicalScore())
                .add(evaluation.getExtraPoints());
        
        evaluation.setTotalScore(totalScore);
        evaluation.setApproved(totalScore.compareTo(new BigDecimal("70")) >= 0);
        
        Evaluation updated = evaluationRepositoryPort.save(evaluation);
        return applicationMapper.toEvaluationResponse(updated);
    }

    @Override
    public void deleteEvaluation(UUID id) {
        if (!evaluationRepositoryPort.findById(id).isPresent()) {
            throw new EvaluationNotFoundException("Evaluation not found with id: " + id);
        }
        evaluationRepositoryPort.delete(id);
    }

}
