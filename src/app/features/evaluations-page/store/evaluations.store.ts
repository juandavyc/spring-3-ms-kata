import { patchState, signalStore, withComputed, withMethods, withProps, withState } from "@ngrx/signals";
import { initialEvaluationsSlice } from "./evaluations.slice";
import { EvaluationsService } from "@core/services/evaluations.service";
import { computed, inject } from "@angular/core";
import { rxMethod } from "@ngrx/signals/rxjs-interop";
import { filter, pipe, switchMap, tap } from "rxjs";
import { tapResponse } from "@ngrx/operators";
import { CrudPageOption } from "@shared/ui/enums/crud-page-option.enum";
import { EvaluationCreateRequest } from "../models/evaluation-create-request.model";



export const EvaluationStore = signalStore(
  withState(initialEvaluationsSlice),
  withProps(() => {
    const _service = inject(EvaluationsService);
    return { _service }
  }),
  withComputed((store) => {

    const hasListResults = computed(() => store.evaluationsParticipants().length > 0);
    const isLoadingList = computed(() => store.loading().list);
    const isLoadingDetails = computed(() => store.loading().details);
    const isDoneDetails = computed(() => store.done().details);

    return {
      hasListResults,
      isLoadingList,
      isLoadingDetails,
      isDoneDetails,
    }

  }),
  withMethods((store) => {

    const rxEvaluationsParticipants = rxMethod<void>(
      pipe(
        tap(() => {
          patchState(store, { loading: { ...store.loading(), list: true } });
          //patchState(store, { evaluationsParticipants: [] });
        }),
        switchMap(() => store._service.getParticipantLastEvaluation().pipe(
          tapResponse({
            next: (response) => {
              const evaluationsParticipants = response;
              patchState(store, { evaluationsParticipants })
            },
            error: (err) => {
              patchState(store, { evaluationsParticipants: [] });
            },
            finalize: () => {
              patchState(store, { loading: { ...store.loading(), list: false } });
            }
          })
        ))
      )
    );

    const rxAccountCreate = rxMethod<EvaluationCreateRequest>(
      pipe(
        tap(() => {
          patchState(store, { loading: { ...store.loading(), details: true } });
          patchState(store, { done: { ...store.done(), details: false } });
          // patchState(store, { message: null })
        }),
        switchMap((request) => store._service.create(request).pipe(
          tapResponse({
            next: (response) => {
              // patchState(store, { account })
              //const accountId = response.data;

              // patchState(store, { message: { severity: 'info', summary: response.status, detail: response.message } })
              patchState(store, { message: { severity: 'success', summary: 'Creado correctamente', detail: response.id } });

              patchState(store, { done: { ...store.done(), details: true } });

              closeDialog();

              setTimeout(() => {
                searchEvaluationsParticipants();
              }, 500);
            },
            error: (err) => {
              patchState(store, { message: { severity: 'error', summary: 'Error', detail: String(err) } });
            },
            finalize: () => {
              patchState(store, { loading: { ...store.loading(), details: false } })

            }
          })
        ))
      )
    );

    const rxById = rxMethod<string | null>(
      pipe(
        filter((id) => id != null),
        tap(() => {
          patchState(store, { loading: { ...store.loading(), details: true } });
          patchState(store, { evaluation: null });
          patchState(store, { message: null })

        }),
        switchMap((id) => store._service.getById(id).pipe(
          tapResponse({
            next: (response) => {
              const evaluation = response;
              patchState(store, { evaluation });
              patchState(store, { message: { severity: 'success', summary: 'Datos encontrados', detail: '' } });
            },
            error: (err) => {
              patchState(store, { evaluation: null });
              patchState(store, { message: { severity: 'error', summary: 'Error', detail: 'An error has occurrend' } });

            },
            finalize: () => {
              patchState(store, { loading: { ...store.loading(), details: false } });
            }
          })
        ))
      )
    );

    const rxDeleteById = rxMethod<string | null>(
      pipe(
        filter((id) => id != null),
        tap(() => {
          patchState(store, { loading: { ...store.loading(), details: true } });
          patchState(store, { done: { ...store.done(), details: false } });
        }),
        switchMap((id) => store._service.deleteById(id).pipe(
          tapResponse({
            next: (response) => {
              //patchState(store, { message: { severity: 'success', summary: response.status, detail: response.message } })
              //const evaluationsParticipants = store.evaluationsParticipants().filter(ep =>
              //  ep.evaluation?.id !== id
              //);
              //patchState(store, { evaluationsParticipants })
              //patchState(store, { done: { ...store.done(), details: true } });
              //patchState(store, { message: { severity: 'success', summary: 'Eliminado correctamente', detail: id } });
//
              closeDialog();

               setTimeout(() => {
                searchEvaluationsParticipants();
              }, 500);
            },
            error: (err) => {
              // patchState(store, { account: null });
            },
            finalize: () => {
              patchState(store, { loading: { ...store.loading(), details: false } })
            }
          })
        ))
      )
    )

    const searchEvaluationsParticipants = () => {
      rxEvaluationsParticipants();
    }

    const searchById = (id: string | null) => {
      console.log("searchById", id);
      rxById(id);
    }

    const deleteById = () => {
      rxDeleteById(store.id());
    }

    const setJudgeId = (judgeId: string) => {
      patchState(store, { judgeId });
    }


    const create = (request: EvaluationCreateRequest) => {
      rxAccountCreate(request);
    }

    const closeDialog = () => {
      patchState(store, { evaluation: null, option: CrudPageOption.NONE, isVisible: false });
      patchState(store, { loading: { ...store.loading(), details: false } })
      patchState(store, { done: { ...store.done(), details: false } });
    }

    const openDialog = (id: string | null, option: CrudPageOption) => {
      patchState(store, { id, evaluation: null, option, isVisible: true });
      if (option === CrudPageOption.UPDATE || option === CrudPageOption.DETAILS) {
         rxById(id);
      }
    }
    return {
      searchEvaluationsParticipants,
      searchById,
      create,
      setJudgeId,
      deleteById,
      openDialog,
      closeDialog,
    }
  })
);
