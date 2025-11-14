package com.juandavyc.evaluations.infrastructure.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record EvaluationRestResponse(
        UUID id,
        UUID participantId,
        UUID judgeId,
        BigDecimal profileScore,
        BigDecimal communicationScore,
        BigDecimal technicalScore,
        BigDecimal extraPoints,
        BigDecimal totalScore,
        Boolean approved,
        LocalDateTime evaluationDate,
        String notes
) {
}
