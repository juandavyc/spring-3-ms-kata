import { CrudPageOption } from "@shared/ui/enums/crud-page-option.enum";
import { ParticipantModel } from "../../../core/models/participant.model";
import { ToastMessage } from "@shared/models/toast-message.model";

export interface ParticipantsSlice {
  participants: ParticipantModel[],
  participant: ParticipantModel | null,
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

export const initialParticipantsSlice: ParticipantsSlice = {
  participants: [],
  participant: null,
  id: null,
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
