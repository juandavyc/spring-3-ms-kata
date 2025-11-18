package com.juandavyc.ranking.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juandavyc.ranking.application.dto.*;
import com.juandavyc.ranking.application.mapper.ParticipantRankingApplicationMapper;
import com.juandavyc.ranking.application.mapper.RankingSnapshotApplicationMapper;
import com.juandavyc.ranking.application.usecases.RankingService;
import com.juandavyc.ranking.domain.model.Participant;
import com.juandavyc.ranking.domain.model.ParticipantRanking;
import com.juandavyc.ranking.domain.model.RankingSnapshot;
import com.juandavyc.ranking.domain.port.ParticipantRankingRepositoryPort;
import com.juandavyc.ranking.domain.port.RankingSnapshotRepositoryPort;
import com.juandavyc.ranking.domain.port.ReportPublisherPort;
import com.juandavyc.ranking.domain.port.query.ParticipantQueryPort;
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

    private final ReportPublisherPort reportPublisherPort;

    private final ParticipantRankingApplicationMapper participantMapper;
    private final RankingSnapshotApplicationMapper snapshotMapper;

    private final ParticipantQueryPort participantPort;


    private final ObjectMapper objectMapper;

    @Override
    public List<ParticipantRankingResponse> listAllParticipantRankings() {
        List<ParticipantRanking> rankingList = participantRepo.findTop3ByOrderByFinalScoreDesc();
        return rankingList.stream().map(ranking -> {
            Participant participant = participantPort.getById(ranking.getParticipantId());
            ParticipantRankingResponse response = participantMapper.toParticipantRankingResponse(ranking);
            response.setParticipant(participantMapper.toParticipantResponse(participant));
            return response;
        }).collect(Collectors.toList());


    }

    @Override
    public ParticipantRankingResponse getParticipantRanking(UUID participantId) {

        ParticipantRanking pr = participantRepo.findByParticipantId(participantId)
                .orElseThrow(() -> new EntityNotFoundException(participantId.toString()));

        Participant participant = participantPort.getById(pr.getParticipantId());

        ParticipantRankingResponse response = participantMapper.toParticipantRankingResponse(pr);
        response.setParticipant(participantMapper.toParticipantResponse(participant));
        return response;
    }


    @Override
    public void createSnapshot(
    ) {
        List<ParticipantRanking> participantRankings = participantRepo.findTop3ByOrderByFinalScoreDesc();

        int total = participantRankings.size();

        BigDecimal average = participantRankings.stream()
                .map(ParticipantRanking::getFinalScore)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(total), RoundingMode.HALF_UP);

        String jsonTop;
        try {
            jsonTop = objectMapper.writeValueAsString(participantRankings);
            reportPublisherPort.publish(jsonTop);
        } catch (JsonProcessingException e) {
            jsonTop = "[]";
        }

        RankingSnapshot snapshot = new RankingSnapshot(
                null,
                LocalDateTime.now(),
                jsonTop,
                total,
                average
        );

        snapshotRepo.save(snapshot);

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
