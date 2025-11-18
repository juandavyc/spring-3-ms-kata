import { Routes } from '@angular/router';
import { EvaluationsLayout } from './evaluations-layout/evaluations-layout';
import { EvaluationStore } from './store/evaluations.store';

const EvaluationsRoutes: Routes = [
  {
    path: '',
    component: EvaluationsLayout,
    providers: [EvaluationStore],
    children: [
      {
        path: '',
        loadComponent: () => import('./pages/evaluations-list/evaluations-list'),
      },
     //{
     //  path: ':id',
     //  loadComponent: () => import('./pages/judges-details-page/judges-details-page'),
     //  resolve: {
     //    data: judgeDetailsResolver
     //  }
     //},
      {
        path: '**',
        redirectTo: '',
      }
    ]
  }
];

export default EvaluationsRoutes;
