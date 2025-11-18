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
    private BigDecimal finalScore;
    private boolean approved;
}
