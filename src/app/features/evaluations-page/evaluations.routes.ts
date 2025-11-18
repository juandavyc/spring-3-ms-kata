import { Routes } from '@angular/router';
import { EvaluationsLayout } from './evaluations-layout/evaluations-layout';
import { EvaluationStore } from './store/evaluations.store';
import { judgeListResolver } from './resolvers/judge-list.resolver';

const EvaluationsRoutes: Routes = [
  {
    path: '',
    component: EvaluationsLayout,
    providers: [EvaluationStore],
    children: [
      {
        path: '',
        loadComponent: () => import('./pages/evaluations-list/evaluations-list'),
        resolve: {
          data: judgeListResolver
        }

      },
      {
        path: '**',
        redirectTo: '',
      }
    ]
  }
];

export default EvaluationsRoutes;
