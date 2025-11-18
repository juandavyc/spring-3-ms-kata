import { inject, Injectable } from '@angular/core';
import { ParticipantsService } from './participants.service';
import { JudgesService } from './judges.service';
import { catchError, Observable, throwError } from 'rxjs';
import { ParticipantModel } from '@core/models/participant.model';
import { APP_CONFIG } from '@core/config/environment.token';
import { EvaluationParticipantModel } from '@core/models/evaluation-participant.model';
import { HttpClient } from '@angular/common/http';
import { EvaluationCreateRequest } from '@features/evaluations-page/models/evaluation-create-request.model';
import { EvaluationModel } from '@core/models/evaluation.model';

@Injectable({
  providedIn: 'root'
})
export class EvaluationsService {


  private readonly participantsService = inject(ParticipantsService);
  private readonly judgesService = inject(JudgesService);

  private http = inject(HttpClient);
  private appConfig = inject(APP_CONFIG);

  constructor() { }

  private getApiUrl(): string {
    return `${this.appConfig.API_URL}/evaluations`;
  }

  getParticipantLastEvaluation(): Observable<EvaluationParticipantModel[]> {
    const url = `${this.getApiUrl()}/latest-by-participant`;
    return this.http.get<EvaluationParticipantModel[]>(url).pipe(
      catchError(this.handleError)
    );
  }

  create(request: EvaluationCreateRequest): Observable<EvaluationModel> {
    const url = `${this.getApiUrl()}`;
    return this.http.post<EvaluationModel>(url, request).pipe(
      catchError(this.handleError)
    );
  }

  getById(id: string): Observable<EvaluationModel> {
      const url = `${this.getApiUrl()}/${id}`;
      return this.http.get<EvaluationModel>(url).pipe(
        catchError(this.handleError)
      );
    }


  deleteById(id: string) {
    const url = `${this.getApiUrl()}/${id}`;
    return this.http.delete(url).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: any) {
    console.error('[ParticipantsService] error:', error);
    return throwError(() => error);
  }

}
