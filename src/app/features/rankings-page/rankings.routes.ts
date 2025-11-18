import { Routes } from '@angular/router';
import { RankingsLayout } from './rankings-layout/rankings-layout';
import { RankingsStore } from './store/rankings.store';

const RankingsRoutes: Routes = [
  {
    path: '',
    component: RankingsLayout,
    providers: [RankingsStore],
    children: [
      {
        path: '',
        loadComponent: () => import('./pages/rankings-list-page/rankings-list-page'),
      },
      {
        path: '**',
        redirectTo: '',
      }
    ]
  }
];

export default RankingsRoutes;
