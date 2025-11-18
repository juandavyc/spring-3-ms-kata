package com.juandavyc.participants.infrastructure.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class ParticipantRestRequest {
    //@NotBlank
    private String name;
    //@Email @NotBlank
    private String email;

    private String jobRole;
}
