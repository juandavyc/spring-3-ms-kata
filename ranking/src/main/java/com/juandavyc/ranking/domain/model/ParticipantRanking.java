package com.juandavyc.ranking.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ParticipantRanking {

    private UUID participantId;
    private String participantName;
    private int totalEvaluations;
    private BigDecimal finalScore;
    private boolean approved;
    private Integer rankPosition;
    private LocalDateTime lastUpdated;

    public ParticipantRanking(UUID participantId,
                              String participantName,
                              int totalEvaluations,
                              BigDecimal finalScore,
                              boolean approved,
                              Integer rankPosition,
                              LocalDateTime lastUpdated) {
        this.participantId = participantId;
        this.participantName = participantName;
        this.totalEvaluations = totalEvaluations;
        this.finalScore = finalScore;
        this.approved = approved;
        this.rankPosition = rankPosition;
        this.lastUpdated = lastUpdated;
    }


    public ParticipantRanking() {
    }


    public UUID getParticipantId() {
        return participantId;
    }

    public void setParticipantId(UUID participantId) {
        this.participantId = participantId;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public int getTotalEvaluations() {
        return totalEvaluations;
    }

    public void setTotalEvaluations(int totalEvaluations) {
        this.totalEvaluations = totalEvaluations;
    }

    public BigDecimal getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(BigDecimal finalScore) {
        this.finalScore = finalScore;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Integer getRankPosition() {
        return rankPosition;
    }

    public void setRankPosition(Integer rankPosition) {
        this.rankPosition = rankPosition;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    // Domain behaviors
    public void updateScore(BigDecimal newScore) {
        this.finalScore = newScore;
        this.lastUpdated = LocalDateTime.now();
    }

    public void updateRankingPosition(int position) {
        this.rankPosition = position;
        this.lastUpdated = LocalDateTime.now();
    }

    public void incrementEvaluations() {
        this.totalEvaluations++;
        this.lastUpdated = LocalDateTime.now();
    }
}
