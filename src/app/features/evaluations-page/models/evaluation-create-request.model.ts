export interface EvaluationCreateRequest {
  participantId: string | null;
  judgeId: string | null;
  profileScore: number | null;
  communicationScore: number | null;
  technicalScore: number | null;
  notes: string | null;
  extraPoints: number | null;
}

