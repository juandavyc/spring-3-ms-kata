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
public class RankingSnapshotResponse {

    private UUID id;
    private LocalDateTime snapshotDate;
    private String topParticipants;
    private Integer totalParticipants;
    private BigDecimal averageScore;

}
