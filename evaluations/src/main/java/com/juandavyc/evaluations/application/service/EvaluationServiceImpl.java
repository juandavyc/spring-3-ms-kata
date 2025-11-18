package com.juandavyc.evaluations.application.service;

import com.juandavyc.evaluations.application.dto.CreateEvaluationCommand;
import com.juandavyc.evaluations.application.dto.EvaluationResponse;
import com.juandavyc.evaluations.application.dto.ParticipantLastEvaluationResponse;
import com.juandavyc.evaluations.application.dto.UpdateEvaluationCommand;
import com.juandavyc.evaluations.application.exceptions.EvaluationNotFoundException;
import com.juandavyc.evaluations.application.mapper.EvaluationApplicationMapper;
import com.juandavyc.evaluations.application.mapper.EvaluationApplicationUpdateMapper;
import com.juandavyc.evaluations.application.usecases.EvaluationUseCase;
import com.juandavyc.evaluations.domain.model.Evaluation;
import com.juandavyc.evaluations.domain.model.Participant;
import com.juandavyc.evaluations.domain.port.event.EvaluationEventPublisherPort;
import com.juandavyc.evaluations.domain.port.query.JudgeQueryPort;
import com.juandavyc.evaluations.domain.port.query.ParticipantQueryPort;
import com.juandavyc.evaluations.domain.port.service.EvaluationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EvaluationServiceImpl implements EvaluationUseCase {

    private final EvaluationServicePort repositoryPort;
    private final EvaluationApplicationMapper mapper;
    private final EvaluationApplicationUpdateMapper updateMapper;

    //
    private final ParticipantQueryPort participantPort;
    private final JudgeQueryPort judgePort;

    // kafka event
    private final EvaluationEventPublisherPort eventPublisherPort;

    @Override
    public EvaluationResponse createEvaluation(CreateEvaluationCommand command) {


        if (!participantPort.existsById(command.participantId())) {
            throw new IllegalArgumentException("Participant not found");
        }
        if (!judgePort.existsById(command.judgeId())) {
            throw new IllegalArgumentException("Judge not found");
        }

        Evaluation evaluation = mapper.toEvaluation(command);

        evaluation.evaluate();

        Evaluation saved = repositoryPort.save(evaluation);

        eventPublisherPort.publishEvaluationCreated(saved);

        return mapper.toEvaluationResponse(saved);
    }

    @Override

    public EvaluationResponse getEvaluationById(UUID id) {
        Evaluation evaluation = repositoryPort.findById(id)
                .orElseThrow(() -> new EvaluationNotFoundException("Evaluation not found with id: " + id));
        return mapper.toEvaluationResponse(evaluation);
    }

    @Override
    public List<EvaluationResponse> getAllEvaluations() {
        return repositoryPort.findAll().stream()
                .map(mapper::toEvaluationResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParticipantLastEvaluationResponse> getLatestEvaluations() {
        List<Participant> participants = participantPort.getAll();

        return participants.stream()
                .map(participant -> {
                    Evaluation lastEval = repositoryPort
                            .findLastEvaluationByParticipantId(participant.getId());
                    return mapper.toParticipantLastEvaluationResponse(participant, lastEval);
                })
                .collect(Collectors.toList());
    }

    @Override
    public EvaluationResponse updateEvaluation(UUID id, UpdateEvaluationCommand command) {
        Evaluation evaluation = repositoryPort.findById(id)
                .orElseThrow(() -> new EvaluationNotFoundException("Evaluation not found with id: " + id));

        updateMapper.updateFromCommand(command, evaluation);

        // Recalculate total score
        BigDecimal totalScore = evaluation.getProfileScore()
                .add(evaluation.getCommunicationScore())
                .add(evaluation.getTechnicalScore())
                .add(evaluation.getExtraPoints());

        evaluation.setTotalScore(totalScore);
        evaluation.setApproved(totalScore.compareTo(new BigDecimal("70")) >= 0);

        Evaluation updated = repositoryPort.save(evaluation);
        return mapper.toEvaluationResponse(updated);
    }

    @Override
    public void deleteEvaluation(UUID id) {
        if (repositoryPort.findById(id).isEmpty()) {
            throw new EvaluationNotFoundException("Evaluation not found with id: " + id);
        }
        repositoryPort.delete(id);
    }

}
