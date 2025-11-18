import { CrudPageOption } from "@shared/ui/enums/crud-page-option.enum";
import { JudgeModel } from "../../../core/models/judge.model";
import { ToastMessage } from "@shared/models/toast-message.model";

export interface JudgesSlice {
  judges: JudgeModel[],
  judge: JudgeModel | null,
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

export const initialJudgesSlice: JudgesSlice = {
  judges: [],
  judge: null,
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
