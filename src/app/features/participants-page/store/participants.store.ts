import { patchState, signalStore, withComputed, withMethods, withProps, withState } from '@ngrx/signals';
import { initialParticipantsSlice } from './participants.slice';
import { ParticipantsService } from '../services/participants.service';
import { computed, inject } from '@angular/core';
import { rxMethod } from '@ngrx/signals/rxjs-interop';
import { tapResponse } from '@ngrx/operators';
import { filter, pipe, switchMap, tap } from 'rxjs';
import { CrudPageOption } from '@shared/ui/enums/crud-page-option.enum';
import { ParticipantRequest } from '../models/participant-request.model';
import { ParticipantModel } from '../models/participant.model';

export const ParticipantsStore = signalStore(
  withState(initialParticipantsSlice),
  withProps(() => {
    const _service = inject(ParticipantsService);
    return { _service }
  }),
  withComputed((store) => {

    const hasListResults = computed(() => store.participants().length > 0);
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

    const rxParticipants = rxMethod<void>(
      pipe(
        tap(() => {
          patchState(store, { loading: { ...store.loading(), list: true } });
          patchState(store, { participants: [] });
        }),
        switchMap(() => store._service.getParticipants().pipe(
          tapResponse({
            next: (response) => {
              const participants = response;
              patchState(store, { participants })
            },
            error: (err) => {
              patchState(store, { participants: [] });
            },
            finalize: () => {
              patchState(store, { loading: { ...store.loading(), list: false } });
            }
          })
        ))
      )
    );


    const rxParticipantById = rxMethod<string | null>(
      pipe(
        filter((id) => id != null),
        tap(() => {
          patchState(store, { loading: { ...store.loading(), details: true } });
          patchState(store, { participant: null });
          patchState(store, { message: null })

        }),
        switchMap((id) => store._service.getParticipantById(id).pipe(
          tapResponse({
            next: (response) => {
              const participant = response;
              patchState(store, { participant });
              patchState(store, { message: { severity: 'success', summary: 'Datos encontrados', detail: participant!.id } });
            },
            error: (err) => {
              patchState(store, { participant: null });
              patchState(store, { message: { severity: 'error', summary: 'Error', detail: 'An error has occurrend' } });

            },
            finalize: () => {
              patchState(store, { loading: { ...store.loading(), details: false } });
            }
          })
        ))
      )
    );

    const rxAccountUpdate = rxMethod<ParticipantRequest>(
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

    const rxAccountCreate = rxMethod<ParticipantRequest>(
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
              const participant: ParticipantModel = response;
              patchState(store, { participants: [...store.participants(), participant] });
              // patchState(store, { message: { severity: 'info', summary: response.status, detail: response.message } })
              patchState(store, { message: { severity: 'success', summary: 'Creado correctamente', detail: participant.name } });

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
        switchMap((id) => store._service.deleteParticipantById(id).pipe(
          tapResponse({
            next: (response) => {
              //patchState(store, { message: { severity: 'success', summary: response.status, detail: response.message } })
              const participants = store.participants().filter(account =>
                account.id !== id
              );
              patchState(store, { participants })
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
      rxParticipants();
    }

    const searchById = (id: string | null) => {
      rxParticipantById(id);
    }

    const deleteById = () => {
      rxDeleteById(store.id());
    }
    const update = (request: ParticipantRequest) => {
      rxAccountUpdate(request);
    }

    const create = (request: ParticipantRequest) => {
      rxAccountCreate(request)
    }

    const closeDialog = () => {
      patchState(store, { id: null, participant: null, option: CrudPageOption.NONE, isVisible: false });
      patchState(store, { loading: { ...store.loading(), details: false } })
      patchState(store, { done: { ...store.done(), details: false } });
    }

    const openDialog = (id: string | null, option: CrudPageOption) => {
      patchState(store, { id, participant: null, option, isVisible: true });
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
