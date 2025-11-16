package com.juandavyc.ranking.infrastructure.rest.controller;

import com.juandavyc.ranking.application.dto.*;
import com.juandavyc.ranking.application.usecases.RankingService;
import com.juandavyc.ranking.infrastructure.rest.dto.ParticipantRankingRestResponse;
import com.juandavyc.ranking.infrastructure.rest.dto.RankingSnapshotRestResponse;
import com.juandavyc.ranking.infrastructure.rest.mapper.ParticipantRankingWebMapper;
import com.juandavyc.ranking.infrastructure.rest.mapper.RankingSnapshotWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/rankings")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService service;
    private final ParticipantRankingWebMapper participantMapper;
    private final RankingSnapshotWebMapper snapshotMapper;

    @GetMapping
    public ResponseEntity<List<ParticipantRankingRestResponse>> getAll() {
        List<ParticipantRankingResponse> response = service.listAllParticipantRankings();
        List<ParticipantRankingRestResponse> dto = response.stream()
                .map(participantMapper::toRest)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/participant/{id}")
    public ResponseEntity<ParticipantRankingRestResponse> getByParticipant(@PathVariable UUID id) {
        ParticipantRankingResponse response = service.getParticipantRanking(id);
        return ResponseEntity
                .ok(participantMapper.toRest(response));
    }

    @PostMapping("/snapshot")
    public ResponseEntity<RankingSnapshotRestResponse> createSnapshot(@RequestBody CreateSnapshotCommand command) {
        RankingSnapshotResponse response = service.createSnapshot(command);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(snapshotMapper.toRest(response));
    }

}
