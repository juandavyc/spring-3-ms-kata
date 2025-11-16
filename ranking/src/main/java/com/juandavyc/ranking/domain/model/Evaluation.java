package com.juandavyc.ranking.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Evaluation {
    private UUID id;
    private UUID participantId;
    private UUID judgeId;
    private BigDecimal profileScore;
    private BigDecimal communicationScore;
    private BigDecimal technicalScore;
    private BigDecimal extraPoints;
    private BigDecimal totalScore;
    private Boolean approved;
    private LocalDateTime evaluationDate;
    private String notes;

    public Evaluation(UUID id, UUID participantId, UUID judgeId, BigDecimal profileScore, BigDecimal communicationScore, BigDecimal technicalScore, BigDecimal extraPoints, BigDecimal totalScore, Boolean approved, LocalDateTime evaluationDate, String notes) {
        this.id = id;
        this.participantId = participantId;
        this.judgeId = judgeId;
        this.profileScore = profileScore;
        this.communicationScore = communicationScore;
        this.technicalScore = technicalScore;
        this.extraPoints = extraPoints;
        this.totalScore = totalScore;
        this.approved = approved;
        this.evaluationDate = evaluationDate;
        this.notes = notes;
    }

    public Evaluation() {
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(LocalDateTime evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public BigDecimal getExtraPoints() {
        return extraPoints;
    }

    public void setExtraPoints(BigDecimal extraPoints) {
        this.extraPoints = extraPoints;
    }

    public BigDecimal getTechnicalScore() {
        return technicalScore;
    }

    public void setTechnicalScore(BigDecimal technicalScore) {
        this.technicalScore = technicalScore;
    }

    public BigDecimal getCommunicationScore() {
        return communicationScore;
    }

    public void setCommunicationScore(BigDecimal communicationScore) {
        this.communicationScore = communicationScore;
    }

    public BigDecimal getProfileScore() {
        return profileScore;
    }

    public void setProfileScore(BigDecimal profileScore) {
        this.profileScore = profileScore;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
