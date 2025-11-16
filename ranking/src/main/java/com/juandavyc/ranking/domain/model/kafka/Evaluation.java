package com.juandavyc.ranking.domain.model.kafka;

import java.util.UUID;

public class Evaluation {
    private UUID id;
    private UUID participantId;
    private UUID judgeId;
    private double totalScore;
    private boolean approved;

    public Evaluation(UUID id, UUID participantId, UUID judgeId, double totalScore, boolean approved) {
        this.id = id;
        this.participantId = participantId;
        this.judgeId = judgeId;
        this.totalScore = totalScore;
        this.approved = approved;
    }



    public Evaluation() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(UUID judgeId) {
        this.judgeId = judgeId;
    }
    public UUID getParticipantId() {
        return participantId;
    }

    public void setParticipantId(UUID participantId) {
        this.participantId = participantId;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
