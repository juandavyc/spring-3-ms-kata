package com.juandavyc.evaluations.infrastructure.rest.dto;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ParticipantRestResponse {
    private UUID id;
    private String name;
    private String jobRole;
    private String email;
}
