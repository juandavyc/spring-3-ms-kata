import { CrudPageOption } from "@shared/ui/enums/crud-page-option.enum";
import { RankingModel } from "../../../core/models/ranking.model";
import { ToastMessage } from "@shared/models/toast-message.model";

export interface RankingsSlice {
  rankings: RankingModel[],
  ranking: RankingModel | null,
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
  isLoadingSnapshot: boolean;
}

export const initialRankingsSlice: RankingsSlice = {
  rankings: [],
  ranking: null,
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
  isLoadingSnapshot: false,
}
