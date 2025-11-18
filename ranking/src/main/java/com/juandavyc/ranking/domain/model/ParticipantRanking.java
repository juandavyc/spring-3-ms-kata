package com.juandavyc.ranking.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

public class ParticipantRanking {

    private UUID participantId;
    private int totalEvaluations;
    private BigDecimal finalScore;
    private boolean approved;
    private Integer rankPosition;
    private LocalDateTime lastUpdated;

    public ParticipantRanking(UUID participantId,
                              int totalEvaluations,
                              BigDecimal finalScore,
                              boolean approved,
                              Integer rankPosition,
                              LocalDateTime lastUpdated) {
        this.participantId = participantId;
        this.totalEvaluations = totalEvaluations;
        this.finalScore = finalScore;
        this.approved = approved;
        this.rankPosition = rankPosition;
        this.lastUpdated = lastUpdated;
    }


    public ParticipantRanking() {
    }

   public void addEvaluation(Evaluation evaluation) {
       this.totalEvaluations++;
       this.finalScore = calculateNewAverage(evaluation.getTotalScore());
       this.approved = this.finalScore.compareTo(new BigDecimal("75")) >= 0;
       this.lastUpdated = LocalDateTime.now();
   }

   private BigDecimal calculateNewAverage(BigDecimal newScore) {
       BigDecimal currentTotal = this.finalScore.multiply(BigDecimal.valueOf(this.totalEvaluations - 1));
       BigDecimal newTotal = currentTotal.add(newScore);
       return newTotal.divide(BigDecimal.valueOf(this.totalEvaluations), 2, RoundingMode.HALF_UP);
   }

    public UUID getParticipantId() {
        return participantId;
    }

    public void setParticipantId(UUID participantId) {
        this.participantId = participantId;
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
