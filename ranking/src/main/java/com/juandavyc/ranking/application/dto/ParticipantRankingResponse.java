package com.juandavyc.ranking.application.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParticipantRankingResponse {
    private ParticipantResponse participant;
    private BigDecimal finalScore;
    private boolean approved;
    private LocalDateTime lastUpdated;

}
