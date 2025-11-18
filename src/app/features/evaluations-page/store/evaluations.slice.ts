import { EvaluationParticipantModel } from "@core/models/evaluation-participant.model";
import { EvaluationModel } from "@core/models/evaluation.model";
import { ToastMessage } from "@shared/models/toast-message.model";
import { CrudPageOption } from "@shared/ui/enums/crud-page-option.enum";


export interface EvaluationsSlice {
  evaluationsParticipants: EvaluationParticipantModel[],
  evaluation: EvaluationModel | null,
  judgeId: string | null,
  id: string | null,
  loading: {
    list: boolean;
    details: boolean;
  },
  done: {
    list: boolean;
    details: boolean;
  },
  isVisible: boolean,
  option: CrudPageOption,
  errors: ToastMessage | null;
  message: ToastMessage | null;
}

export const initialEvaluationsSlice: EvaluationsSlice = {
  evaluationsParticipants: [],
  evaluation: null,
  judgeId: '69606446-f433-4a50-9ac5-116cc0fe091a',
  id: '84c27ba5-aea7-41a3-98dd-d943780cd80d',
  loading: {
    list: false,
    details: false,
  },
  done: {
    list: false,
    details: false,
  },
  isVisible: false,
  option: CrudPageOption.NONE,
  errors: null,
  message: null,
}
