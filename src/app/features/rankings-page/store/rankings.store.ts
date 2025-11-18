import { patchState, signalStore, withComputed, withMethods, withProps, withState } from '@ngrx/signals';
import { initialRankingsSlice } from './rankings.slice';
import { computed, inject } from '@angular/core';
import { rxMethod } from '@ngrx/signals/rxjs-interop';
import { tapResponse } from '@ngrx/operators';
import { pipe, switchMap, tap } from 'rxjs';
import { CrudPageOption } from '@shared/ui/enums/crud-page-option.enum';
import { RankingRequest } from '../models/ranking-request.model';
import { RankingModel } from '../../../core/models/ranking.model';
import { RankingsService } from '@core/services/rankings.service';

export const RankingsStore = signalStore(
  withState(initialRankingsSlice),
  withProps(() => {
    const _service = inject(RankingsService);
    return { _service }
  }),
  withComputed((store) => {

    const hasListResults = computed(() => store.rankings().length > 0);
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

    const rxRankings = rxMethod<void>(
      pipe(
        tap(() => {
          patchState(store, { loading: { ...store.loading(), list: true } });
          patchState(store, { rankings: [] });
        }),
        switchMap(() => store._service.getRankings().pipe(
          tapResponse({
            next: (response) => {
              const rankings = response;
              patchState(store, { rankings })
            },
            error: (err) => {
              patchState(store, { rankings: [] });
            },
            finalize: () => {
              patchState(store, { loading: { ...store.loading(), list: false } });
            }
          })
        ))
      )
    );

    const rxSnapshotCreate = rxMethod<void>(
      pipe(
        tap(() => {
          patchState(store, { loading: { ...store.loading(), details: true } });
          patchState(store, { isLoadingSnapshot: true });
        }),
        switchMap(() => store._service.createSnapshot().pipe(
          tapResponse({
            next: (response) => {
              //const ranking: RankingModel = response;
              //patchState(store, { rankings: [...store.rankings(), ranking] });
              patchState(store, { message: { severity: 'success', summary: 'Creado correctamente', detail: 'Snapshot en la nube' } });
            },
            error: (err) => {
              patchState(store, { message: { severity: 'error', summary: 'Error', detail: String(err) } });
            },
            finalize: () => {
              patchState(store, { loading: { ...store.loading(), details: false } })
              patchState(store, { isLoadingSnapshot: false });
            }
          })
        ))
      )
    );

    const search = () => {
      rxRankings();
    }

    const create = () => {
      rxSnapshotCreate()
    }

    const closeDialog = () => {
      patchState(store, { id: null, ranking: null, option: CrudPageOption.NONE, isVisible: false });
      patchState(store, { loading: { ...store.loading(), details: false } })
      patchState(store, { done: { ...store.done(), details: false } });
    }

    const openDialog = (id: string | null, option: CrudPageOption) => {
      patchState(store, { id, ranking: null, option, isVisible: true });
    }

    return {
      search,
      create,
      openDialog,
      closeDialog
    }

  })

);
