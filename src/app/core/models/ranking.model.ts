export interface RankingModel {
  participant: Participant;
  finalScore:  number;
  approved:    boolean;
  lastUpdated: Date;
}

interface Participant {
  id:      string;
  name:    string;
  jobRole: null;
  email:   string;
}
