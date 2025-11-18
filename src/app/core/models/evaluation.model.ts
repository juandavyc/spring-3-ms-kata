export interface EvaluationModel {
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
