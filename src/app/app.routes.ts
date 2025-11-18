import { Routes } from '@angular/router';
import { HomePage } from './features/home-page/home-page';

export const routes: Routes = [

  {
      path: '',
      component: HomePage,
    },
    {
      path: 'participants',
      loadChildren: () => import('./features/participants-page/participants.routes'),
    },
    {
      path: 'judges',
      loadChildren: () => import('./features/judges-page/judges.routes'),
    },
    {
      path: 'evaluations',
      loadChildren: () => import('./features/evaluations-page/evaluations.routes'),
    },
    {
      path: 'rankings',
      loadChildren: () => import('./features/rankings-page/rankings.routes'),
    }

];
