package com.juandavyc.ranking.infrastructure.adapter;

import com.juandavyc.ranking.domain.model.kafka.Evaluation;
import com.juandavyc.ranking.domain.port.ParticipantRankingRepositoryPort;
import com.juandavyc.ranking.domain.service.EvaluationProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationProcessorAdapter implements EvaluationProcessor {


    private final ParticipantRankingRepositoryPort participantRankingRepository;

    @Override
    public void processEvaluation(Evaluation event) {

        System.out.println("Procesando evaluación para candidato: ");
        System.out.println("ACA EL SAVED: " + event.getId() + event.getParticipantId());

        //System.out.println("Procesando evaluación para candidato: " + event.getCandidateId());

        //// Validar score
        //if (event.getScore() < 0 || event.getScore() > 100) {
        //    throw new IllegalArgumentException("Score inválido: " + event.getScore());
        //}
//
        //// Buscar o crear ParticipantRanking
        //ParticipantRanking ranking = participantRankingRepository.findByCandidateId(event.getCandidateId())
        //        .orElse(new ParticipantRanking(event.getCandidateId(), 0, 0, 0.0));
//
        //// Actualizar estadísticas
        //ranking.setTotalEvaluations(ranking.getTotalEvaluations() + 1);
        //ranking.setTotalScore(ranking.getTotalScore() + event.getScore());
        //ranking.setAverageScore((double) ranking.getTotalScore() / ranking.getTotalEvaluations());
//
        //// Guardar
        //participantRankingRepository.save(ranking);
//
        //System.out.println("Ranking actualizado para candidato: " + event.getCandidateId() +
        //        " - Promedio: " + ranking.getAverageScore());

    }

}
