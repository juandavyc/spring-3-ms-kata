package com.juandavyc.ranking.infrastructure.rest.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParticipantRankingRestResponse {

    private UUID participantId;
    private String participantName;
    private int totalEvaluations;
    private BigDecimal finalScore;
    private boolean approved;
    private Integer rankPosition;
    private LocalDateTime lastUpdated;

}
