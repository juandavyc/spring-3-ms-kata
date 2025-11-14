package com.juandavyc.evaluations.infrastructure.adapter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "evaluations", schema = "evaluations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EvaluationEntity {

    @Id
    @GeneratedValue(generator = "UUID")
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

}
