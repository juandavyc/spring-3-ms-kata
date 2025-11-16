package com.juandavyc.ranking.infrastructure.kafka.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class EvaluationEvent {
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
