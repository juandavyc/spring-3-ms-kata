import { inject } from '@angular/core';

import { ResolveFn, Router } from '@angular/router';
import { JudgesService } from '@core/services/judges.service';
import { catchError, of } from 'rxjs';



export const judgeListResolver: ResolveFn<any> = (route) => {

  const service = inject(JudgesService);
  const router = inject(Router);


  return service.getJudges().pipe(
    catchError(() => {
      return of(null);
    })
  );

};
