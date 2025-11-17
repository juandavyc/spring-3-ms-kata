import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { APP_CONFIG } from '@core/config/environment.token';
import { ParticipantModel } from '../models/participant.model';
import { catchError, delay, Observable, throwError } from 'rxjs';
import { ParticipantRequest } from '../models/participant-request.model';

@Injectable({
  providedIn: 'root'
})
export class ParticipantsService {

  private http = inject(HttpClient);
  private appConfig = inject(APP_CONFIG);

  constructor() { }

  private getApiUrl(): string {
    return `${this.appConfig.API_URL}/participants`
      ;
  }

  getParticipants(): Observable<ParticipantModel[]> {
    const url = `${this.getApiUrl()}`;
    return this.http.get<ParticipantModel[]>(url).pipe(
      catchError(this.handleError)
    );
  }

  getParticipantById(id: string): Observable<ParticipantModel> {
    const url = `${this.getApiUrl()}/${id}`;
    return this.http.get<ParticipantModel>(url).pipe(
      catchError(this.handleError)
    );
  }

  isAvailableEmail(email:string):Observable<Boolean>{
    const url = `${this.getApiUrl()}/email/${email}/exists`;
    return this.http.get<Boolean>(url).pipe(
      catchError(this.handleError)
    );
  }

  deleteParticipantById(id: string) {
    const url = `${this.getApiUrl()}/${id}`;
    return this.http.delete(url).pipe(
      catchError(this.handleError)
    );
  }

  create(request: ParticipantRequest): Observable<ParticipantModel> {
    const url = `${this.getApiUrl()}`;
    return this.http.post<ParticipantModel>(url, request).pipe(
      catchError(this.handleError)
    );
  }

  updateAccount(id: string, request: ParticipantRequest): Observable<ParticipantModel> {
    const url = `${this.getApiUrl()}/${id}`;
    return this.http.put<ParticipantModel>(url, request).pipe(
      catchError(this.handleError)
    );
  }



  private handleError(error: any) {
    console.error('[ParticipantsService] error:', error);
    return throwError(() => error);
  }

}
