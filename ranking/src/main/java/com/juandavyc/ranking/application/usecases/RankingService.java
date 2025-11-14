package com.juandavyc.ranking.application.usecases;

import com.juandavyc.ranking.application.dto.*;

import java.util.List;
import java.util.UUID;

public interface RankingService {

    List<ParticipantRankingResponse> listAllParticipantRankings();

    ParticipantRankingResponse getParticipantRanking(UUID participantId);

    ParticipantRankingResponse updateParticipantRanking(UUID participantId, ParticipantRankingResponse update);

    RankingSnapshotResponse createSnapshot(CreateSnapshotCommand command);

    List<RankingSnapshotResponse> getAllSnapshots();

    RankingSnapshotResponse getSnapshotById(UUID id);

}
