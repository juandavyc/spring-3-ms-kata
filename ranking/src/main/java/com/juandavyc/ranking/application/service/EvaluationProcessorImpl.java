package com.juandavyc.ranking.application.service;

import com.juandavyc.ranking.application.dto.EvaluationCommand;
import com.juandavyc.ranking.application.mapper.EvaluationApplicationMapper;
import com.juandavyc.ranking.application.usecases.EvaluationUseCase;
import com.juandavyc.ranking.domain.model.Evaluation;
import com.juandavyc.ranking.domain.model.ParticipantRanking;
import com.juandavyc.ranking.domain.port.ParticipantRankingRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EvaluationProcessorImpl implements EvaluationUseCase {

    private final ParticipantRankingRepositoryPort rankingRepository;
    private final EvaluationApplicationMapper mapper;

    @Override
    public void processEvaluation(EvaluationCommand command) {

        Evaluation evaluation = mapper.toDomain(command);

        ParticipantRanking participantRanking = rankingRepository
                .findByParticipantId(evaluation.getParticipantId())
                .orElseGet(() -> createNewParticipantRanking(evaluation));

        participantRanking.addEvaluation(evaluation);

        rankingRepository.save(participantRanking);
    }


    private ParticipantRanking createNewParticipantRanking(Evaluation evaluation) {

        return new ParticipantRanking(
                evaluation.getParticipantId(),
                evaluation.getParticipantId() + "-name",
                0,
                evaluation.getTotalScore(),
                false,
                0,
                LocalDateTime.now()
        );
    }

}
