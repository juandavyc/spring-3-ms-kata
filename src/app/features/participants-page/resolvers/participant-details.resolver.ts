import { inject } from '@angular/core';
import { ResolveFn, Router } from '@angular/router';
import { ParticipantsService } from '@core/services/participants.service';
import { catchError, of } from 'rxjs';


export const participantDetailsResolver: ResolveFn<any> = (route) => {

  const service = inject(ParticipantsService);
  const router = inject(Router);
  const id = route.paramMap.get('id');

  if (!id) {
    router.navigate(['/participants']);
    return of(null);
  }

  return service.getParticipantById(id).pipe(
    catchError(() => {
      router.navigate(['/participants']);
      return of(null);
    })
  );
};
