package com.juandavyc.evaluations.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateEvaluationCommand(
        UUID participantId,
        UUID judgeId,
        BigDecimal profileScore,
        BigDecimal communicationScore,
        BigDecimal technicalScore,
        BigDecimal extraPoints,
        String notes
) {
}
