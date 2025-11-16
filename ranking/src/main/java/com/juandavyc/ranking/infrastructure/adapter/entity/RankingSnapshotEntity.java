package com.juandavyc.ranking.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ranking_snapshots", schema = "ranking")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RankingSnapshotEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private LocalDateTime snapshotDate;

    private String topParticipants = "[]";

    private Integer totalParticipants;
    private BigDecimal averageScore;

}
