package com.juandavyc.ranking.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateSnapshotCommand {

    private LocalDateTime snapshotDate;
    private String topParticipants; // JSON as String
    private Integer totalParticipants;
    private BigDecimal averageScore;

}
