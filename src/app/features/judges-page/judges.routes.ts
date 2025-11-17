import { Routes } from '@angular/router';
import { JudgesLayout } from './judges-layout/judges-layout';
import { JudgesStore } from './store/judges.store';
import { judgeDetailsResolver } from './resolvers/judge-details.resolver';

const JudgesRoutes: Routes = [
  {
    path: '',
    component: JudgesLayout,
    providers: [JudgesStore],
    children: [
      {
        path: '',
        loadComponent: () => import('./pages/judges-list-page/judges-list-page'),
      },
      {
        path: ':id',
        loadComponent: () => import('./pages/judges-details-page/judges-details-page'),
        resolve: {
          data: judgeDetailsResolver
        }
      },
      {
        path: '**',
        redirectTo: '',
      }
    ]
  }
];

export default JudgesRoutes;
