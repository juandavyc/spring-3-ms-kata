package com.juandavyc.participants.infrastructure.rest.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JudgeRestResponse {

    private UUID id;
    private String name;
    private String specialization;
    private LocalDateTime createdAt;

}
