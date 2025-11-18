package com.juandavyc.evaluations.application.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ParticipantResponse {
    private UUID id;
    private String name;
    private String jobRole;
    private String email;
}
