package com.juandavyc.ranking.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juandavyc.ranking.application.dto.*;
import com.juandavyc.ranking.application.mapper.ParticipantRankingApplicationMapper;
import com.juandavyc.ranking.application.mapper.RankingSnapshotApplicationMapper;
import com.juandavyc.ranking.application.usecases.RankingService;
import com.juandavyc.ranking.domain.model.ParticipantRanking;
import com.juandavyc.ranking.domain.model.RankingSnapshot;
import com.juandavyc.ranking.domain.port.ParticipantRankingRepositoryPort;
import com.juandavyc.ranking.domain.port.RankingSnapshotRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {

    private final ParticipantRankingRepositoryPort participantRepo;
    private final RankingSnapshotRepositoryPort snapshotRepo;

    private final ParticipantRankingApplicationMapper participantMapper;
    private final RankingSnapshotApplicationMapper snapshotMapper;
    private final ObjectMapper objectMapper;

    @Override
    public List<ParticipantRankingResponse> listAllParticipantRankings() {
        List<ParticipantRanking> list = participantRepo.findAll();
        return list.stream().map(participantMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public ParticipantRankingResponse getParticipantRanking(UUID participantId) {
        ParticipantRanking pr = participantRepo.findByParticipantId(participantId)
                .orElseThrow(() -> new EntityNotFoundException(participantId.toString()));
        return participantMapper.toResponse(pr);
    }

    @Override
    public ParticipantRankingResponse updateParticipantRanking(UUID participantId, ParticipantRankingResponse update) {
        ParticipantRanking existing = participantRepo.findByParticipantId(participantId)
                .orElseThrow(() -> new EntityNotFoundException(participantId.toString()));
        // simple update behaviour: update score and rank if present
        if (update.getFinalScore() != null) {
            existing.updateScore(update.getFinalScore());
        }
        if (update.getRankPosition() != null) {
            existing.updateRankingPosition(update.getRankPosition());
        }
        if (update.getTotalEvaluations() > 0) {
            update.setTotalEvaluations(update.getTotalEvaluations() + 1);
        }
        ParticipantRanking saved = participantRepo.save(existing);
        return participantMapper.toResponse(saved);
    }

    @Override
    public RankingSnapshotResponse createSnapshot(
    ) {
        List<ParticipantRanking> participantRankings = participantRepo.findAll();
//
        // 1. Ordenar por puntaje DESC
        List<ParticipantRanking> top = participantRankings.stream()
                .sorted(Comparator.comparing(ParticipantRanking::getFinalScore).reversed())
                .toList();
//
        // 2. Asignar rankPosition
        AtomicInteger position = new AtomicInteger(1);
        top.forEach(p -> p.setRankPosition(position.getAndIncrement()));
//
        // 3. Calcular total
        int total = participantRankings.size();
//
        // 4. Calcular promedio
        BigDecimal average = participantRankings.stream()
                .map(ParticipantRanking::getFinalScore)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(total), RoundingMode.HALF_UP);
//
        // 5. Pasar a JSON
        String jsonTop;
        try {
            jsonTop = objectMapper.writeValueAsString(top);
        } catch (JsonProcessingException e) {
            jsonTop = "[]"; // Mejor: array vacío, no "{}"
        }
//
        // 6. Crear snapshot (TODAVÍA EN DOMAIN)
        RankingSnapshot snapshot = new RankingSnapshot(
                null,
                LocalDateTime.now(),
                jsonTop,
                total,
                average
        );
//
        // 7. Guardar en repository (si quieres)
        RankingSnapshot saved = snapshotRepo.save(snapshot);
//
        // 8. Devolver response
        return snapshotMapper.toResponse(saved);

    }

    @Override
    public List<RankingSnapshotResponse> getAllSnapshots() {
        List<RankingSnapshot> list = snapshotRepo.findAll();
        return list.stream().map(snapshotMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public RankingSnapshotResponse getSnapshotById(UUID id) {
        RankingSnapshot s = snapshotRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
        return snapshotMapper.toResponse(s);
    }


}
