import { inject } from '@angular/core';

import { ResolveFn, Router } from '@angular/router';
import { catchError, of } from 'rxjs';
import { JudgesService } from '../../../core/services/judges.service';


export const judgeDetailsResolver: ResolveFn<any> = (route) => {

  const service = inject(JudgesService);
  const router = inject(Router);
  const id = route.paramMap.get('id');

  if (!id) {
    router.navigate(['/judges']);
    return of(null);
  }

  return service.getJudgeById(id).pipe(
    catchError(() => {
      router.navigate(['/judges']);
      return of(null);
    })
  );

};
