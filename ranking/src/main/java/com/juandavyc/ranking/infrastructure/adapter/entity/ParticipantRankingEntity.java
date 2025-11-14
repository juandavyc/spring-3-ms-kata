package com.juandavyc.ranking.infrastructure.adapter.entity;

import jakarta.persistence.Entity;
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
@Table(name = "participant_rankings", schema = "ranking")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParticipantRankingEntity {

    @Id
    private UUID participantId;
    private String participantName;
    private int totalEvaluations;
    private BigDecimal finalScore;
    private boolean approved;
    private Integer rankPosition;
    private LocalDateTime lastUpdated;

}
