package com.juandavyc.judges.application.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JudgeResponse {

    private UUID id;
    private String name;
    private String email;
    private String specialization;
    private LocalDateTime createdAt;
}