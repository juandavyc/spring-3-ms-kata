package com.juandavyc.ranking.infrastructure.feign.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParticipantRestResponse {

    private UUID id;
    private String name;
    private String email;
    private String jobRole;
    private LocalDateTime createdAt;

}