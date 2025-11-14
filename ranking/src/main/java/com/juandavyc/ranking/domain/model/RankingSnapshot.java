package com.juandavyc.ranking.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class RankingSnapshot {

    private UUID id;
    private LocalDateTime snapshotDate;
    private String topParticipants; // JSONB → String en domain
    private Integer totalParticipants;
    private BigDecimal averageScore;

    public RankingSnapshot(UUID id,
                           LocalDateTime snapshotDate,
                           String topParticipants,
                           Integer totalParticipants,
                           BigDecimal averageScore) {
        this.id = id;
        this.snapshotDate = snapshotDate;
        this.topParticipants = topParticipants;
        this.totalParticipants = totalParticipants;
        this.averageScore = averageScore;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getSnapshotDate() {
        return snapshotDate;
    }

    public String getTopParticipants() {
        return topParticipants;
    }

    public Integer getTotalParticipants() {
        return totalParticipants;
    }

    public BigDecimal getAverageScore() {
        return averageScore;
    }
}
