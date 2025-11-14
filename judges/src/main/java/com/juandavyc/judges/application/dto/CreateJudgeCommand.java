package com.juandavyc.judges.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateJudgeCommand {

    private String name;
    private String email;
    private String specialization;

}