import { AbstractControl, AsyncValidatorFn } from '@angular/forms';
import { catchError, map } from 'rxjs/operators';
import { JudgesService } from '../services/judges.service';
import { inject } from '@angular/core';
import { of } from 'rxjs';

export function isAvailableEmailValidator(): AsyncValidatorFn {
  const service = inject(JudgesService);

 return (control: AbstractControl) => {
    if (!control.value) return of(null);
    return service.isAvailableEmail(control.value).pipe(
      map(isTaken => (isTaken ? { emailTaken: true } : null)),
      catchError(() => of(null))
    );
  };

}
