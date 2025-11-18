package com.juandavyc.evaluations.application.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParticipantLastEvaluationResponse {

   private ParticipantResponse participant;
   private EvaluationResponse evaluation;

}
