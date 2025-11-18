import { patchState, signalStore, withComputed, withMethods, withProps, withState } from '@ngrx/signals';
import { initialJudgesSlice } from './judges.slice';
import { JudgesService } from '../../../core/services/judges.service';
import { computed, inject } from '@angular/core';
import { rxMethod } from '@ngrx/signals/rxjs-interop';
import { tapResponse } from '@ngrx/operators';
import { filter, pipe, switchMap, tap } from 'rxjs';
import { CrudPageOption } from '@shared/ui/enums/crud-page-option.enum';
import { JudgeRequest } from '../models/judge-request.model';
import { JudgeModel } from '../../../core/models/judge.model';

export const JudgesStore = signalStore(
  withState(initialJudgesSlice),
  withProps(() => {
    const _service = inject(JudgesService);
    return { _service }
  }),
  withComputed((store) => {

    const hasListResults = computed(() => store.judges().length > 0);
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

    const rxJudges = rxMethod<void>(
      pipe(
        tap(() => {
          patchState(store, { loading: { ...store.loading(), list: true } });
          patchState(store, { judges: [] });
        }),
        switchMap(() => store._service.getJudges().pipe(
          tapResponse({
            next: (response) => {
              const judges = response;
              patchState(store, { judges })
            },
            error: (err) => {
              patchState(store, { judges: [] });
            },
            finalize: () => {
              patchState(store, { loading: { ...store.loading(), list: false } });
            }
          })
        ))
      )
    );


    const rxJudgeById = rxMethod<string | null>(
      pipe(
        filter((id) => id != null),
        tap(() => {
          patchState(store, { loading: { ...store.loading(), details: true } });
          patchState(store, { judge: null });
          patchState(store, { message: null })

        }),
        switchMap((id) => store._service.getJudgeById(id).pipe(
          tapResponse({
            next: (response) => {
              const judge = response;
              patchState(store, { judge });
              patchState(store, { message: { severity: 'success', summary: 'Datos encontrados', detail: judge!.id } });
            },
            error: (err) => {
              patchState(store, { judge: null });
              patchState(store, { message: { severity: 'error', summary: 'Error', detail: 'An error has occurrend' } });

            },
            finalize: () => {
              patchState(store, { loading: { ...store.loading(), details: false } });
            }
          })
        ))
      )
    );

    const rxAccountUpdate = rxMethod<JudgeRequest>(
      pipe(
        tap(() => {
          patchState(store, { loading: { ...store.loading(), details: true } });
          patchState(store, { loading: { ...store.done(), details: false } });

          //patchState(store, { message: null })
        }),
        switchMap((request) => store._service.updateAccount(store.id()!, request).pipe(
          tapResponse({
            next: (response) => {
              //const account = response.data;
              //patchState(store, { account })
              // patchState(store, { message: { severity: 'info', summary: response.status, detail: response.message } })

              //const accounts = store.accounts().map(account =>
              //  account.id == response.data.id ? response.data : account
              //);
              //patchState(store, { accounts })
              patchState(store, { message: { severity: 'success', summary: 'Datos actualizados', detail: store.id()! } });
              patchState(store, { loading: { ...store.done(), details: true } });

            },
            error: (err) => {
              patchState(store, { message: { severity: 'error', summary: 'Error', detail: 'An error has occurrend' } });
              // patchState(store, { account: null });
            },
            finalize: () => {
              patchState(store, { loading: { ...store.loading(), details: false } })


            }
          })
        ))
      )
    );

    const rxAccountCreate = rxMethod<JudgeRequest>(
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
              const judge: JudgeModel = response;
              patchState(store, { judges: [...store.judges(), judge] });
              // patchState(store, { message: { severity: 'info', summary: response.status, detail: response.message } })
              patchState(store, { message: { severity: 'success', summary: 'Creado correctamente', detail: judge.name } });

              patchState(store, { done: { ...store.done(), details: true } });
              closeDialog();
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

    const rxDeleteById = rxMethod<string | null>(
      pipe(
        filter((id) => id != null),
        tap(() => {
          patchState(store, { loading: { ...store.loading(), details: true } });
          patchState(store, { done: { ...store.done(), details: false } });
        }),
        switchMap((id) => store._service.deleteJudgeById(id).pipe(
          tapResponse({
            next: (response) => {
              //patchState(store, { message: { severity: 'success', summary: response.status, detail: response.message } })
              const judges = store.judges().filter(account =>
                account.id !== id
              );
              patchState(store, { judges })
              patchState(store, { done: { ...store.done(), details: true } });
              patchState(store, { message: { severity: 'success', summary: 'Eliminado correctamente', detail: id } });

              closeDialog();
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

    const search = () => {
      rxJudges();
    }

    const searchById = (id: string | null) => {
      rxJudgeById(id);
    }

    const deleteById = () => {
      rxDeleteById(store.id());
    }
    const update = (request: JudgeRequest) => {
      rxAccountUpdate(request);
    }

    const create = (request: JudgeRequest) => {
      rxAccountCreate(request)
    }

    const closeDialog = () => {
      patchState(store, { id: null, judge: null, option: CrudPageOption.NONE, isVisible: false });
      patchState(store, { loading: { ...store.loading(), details: false } })
      patchState(store, { done: { ...store.done(), details: false } });
    }

    const openDialog = (id: string | null, option: CrudPageOption) => {
      patchState(store, { id, judge: null, option, isVisible: true });
      if (option === CrudPageOption.UPDATE) {
        searchById(id);
      }
    }


    return {
      search,
      searchById,
      deleteById,
      update,
      create,
      openDialog,
      closeDialog
    }

  })

);
