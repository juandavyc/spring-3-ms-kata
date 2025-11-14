package com.juandavyc.evaluations.infrastructure.rest.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record EvaluationRestRequest(
        UUID participantId,
        UUID judgeId,
        BigDecimal profileScore,
        BigDecimal communicationScore,
        BigDecimal technicalScore,
        BigDecimal extraPoints,
        String notes
) {
}
