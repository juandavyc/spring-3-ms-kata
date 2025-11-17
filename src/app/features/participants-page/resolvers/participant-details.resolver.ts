import { inject } from '@angular/core';

import { ResolveFn, Router } from '@angular/router';
import { catchError, of } from 'rxjs';
import { ParticipantsService } from '../services/participants.service';


export const participantDetailsResolver: ResolveFn<any> = (route) => {

  const service = inject(ParticipantsService);
  const router = inject(Router);
  const id = route.paramMap.get('id')!;

  return service.getParticipantById(id).pipe(
    catchError(() => {
      router.navigate(['/participants']);
      return of(null);
    })
  );
};
