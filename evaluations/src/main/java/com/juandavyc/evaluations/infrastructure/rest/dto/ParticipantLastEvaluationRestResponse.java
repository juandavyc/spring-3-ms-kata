package com.juandavyc.evaluations.infrastructure.rest.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParticipantLastEvaluationRestResponse {

   private ParticipantRestResponse participant;
   private EvaluationRestResponse evaluation;

}
