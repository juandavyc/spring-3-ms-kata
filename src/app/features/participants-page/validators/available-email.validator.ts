import { AbstractControl, AsyncValidatorFn } from '@angular/forms';
import { catchError, map } from 'rxjs/operators';
import { ParticipantsService } from '../services/participants.service';
import { inject } from '@angular/core';
import { of } from 'rxjs';

export function isAvailableEmailValidator(): AsyncValidatorFn {
  const service = inject(ParticipantsService);

 return (control: AbstractControl) => {
    if (!control.value) return of(null);
    return service.isAvailableEmail(control.value).pipe(
      map(isTaken => (isTaken ? { emailTaken: true } : null)),
      catchError(() => of(null))
    );
  };

}
