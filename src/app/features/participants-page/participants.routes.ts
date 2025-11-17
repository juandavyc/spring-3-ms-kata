import { Routes } from '@angular/router';
import { ParticipantsLayout } from './participants-layout/participants-layout';
import { ParticipantsStore } from './store/participants.store';
import { participantDetailsResolver } from './resolvers/participant-details.resolver';

const ParticipantsRoutes: Routes = [
  {
    path: '',
    component: ParticipantsLayout,
    providers: [ParticipantsStore],
    children: [
      {
        path: '',
        loadComponent: () => import('./pages/participants-list-page/participants-list-page'),
      },
      {
        path: ':id',
        loadComponent: () => import('./pages/participants-details-page/participants-details-page'),
        resolve: {
          data: participantDetailsResolver
        }
      },
      {
        path: '**',
        redirectTo: '',
      }
    ]
  }
];

export default ParticipantsRoutes;
