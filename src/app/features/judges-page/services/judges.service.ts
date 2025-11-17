import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { APP_CONFIG } from '@core/config/environment.token';
import { JudgeModel } from '../models/judge.model';
import { catchError, delay, Observable, throwError } from 'rxjs';
import { JudgeRequest } from '../models/judge-request.model';

@Injectable({
  providedIn: 'root'
})
export class JudgesService {

  private http = inject(HttpClient);
  private appConfig = inject(APP_CONFIG);

  constructor() { }

  private getApiUrl(): string {
    return `${this.appConfig.API_URL}/judges`
      ;
  }

  getJudges(): Observable<JudgeModel[]> {
    const url = `${this.getApiUrl()}`;
    return this.http.get<JudgeModel[]>(url).pipe(
      catchError(this.handleError)
    );
  }

  getJudgeById(id: string): Observable<JudgeModel> {
    const url = `${this.getApiUrl()}/${id}`;
    return this.http.get<JudgeModel>(url).pipe(
      catchError(this.handleError)
    );
  }

  isAvailableEmail(email:string):Observable<Boolean>{
    const url = `${this.getApiUrl()}/email/${email}/exists`;
    return this.http.get<Boolean>(url).pipe(
      catchError(this.handleError)
    );
  }

  deleteJudgeById(id: string) {
    const url = `${this.getApiUrl()}/${id}`;
    return this.http.delete(url).pipe(
      catchError(this.handleError)
    );
  }

  create(request: JudgeRequest): Observable<JudgeModel> {
    const url = `${this.getApiUrl()}`;
    return this.http.post<JudgeModel>(url, request).pipe(
      catchError(this.handleError)
    );
  }

  updateAccount(id: string, request: JudgeRequest): Observable<JudgeModel> {
    const url = `${this.getApiUrl()}/${id}`;
    return this.http.put<JudgeModel>(url, request).pipe(
      catchError(this.handleError)
    );
  }



  private handleError(error: any) {
    console.error('[JudgesService] error:', error);
    return throwError(() => error);
  }

}
