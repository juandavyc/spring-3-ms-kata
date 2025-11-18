export interface EvaluationParticipantModel {
  participant: Participant;
  evaluation:  Evaluation | null;
}

export interface Evaluation {
  id:                 string;
  participantId:      string;
  judgeId:            string;
  profileScore:       number;
  communicationScore: number;
  technicalScore:     number;
  extraPoints:        number;
  totalScore:         number;
  approved:           boolean;
  evaluationDate:     string;
  notes:              string;
}

export interface Participant {
  id:    string;
  name:  string;
  email: string;
}
