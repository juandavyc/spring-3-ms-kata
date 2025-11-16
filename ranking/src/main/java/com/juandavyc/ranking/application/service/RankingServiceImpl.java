package com.juandavyc.ranking.application.service;

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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {

    private final ParticipantRankingRepositoryPort participantRepo;
    private final RankingSnapshotRepositoryPort snapshotRepo;

    private final ParticipantRankingApplicationMapper participantMapper;
    private final RankingSnapshotApplicationMapper snapshotMapper;

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
            // naive: set count (domain has no setter so we skip)
        }
        ParticipantRanking saved = participantRepo.save(existing);
        return participantMapper.toResponse(saved);
    }

    @Override
    public RankingSnapshotResponse createSnapshot(CreateSnapshotCommand command) {
        RankingSnapshot snapshot = snapshotMapper.toDomain(command);
        RankingSnapshot saved = snapshotRepo.save(snapshot);
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
