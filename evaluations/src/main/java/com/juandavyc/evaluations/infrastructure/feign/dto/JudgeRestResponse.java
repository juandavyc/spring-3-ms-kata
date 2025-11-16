package com.juandavyc.evaluations.infrastructure.feign.dto;

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
    private String email;
    private String specialization;
    private LocalDateTime createdAt;

}
