import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { APP_CONFIG } from '@core/config/environment.token';
import { RankingModel } from '@core/models/ranking.model';
import { RankingRequest } from '@features/rankings-page/models/ranking-request.model';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RankingsService {


  private http = inject(HttpClient);
  private appConfig = inject(APP_CONFIG);

  constructor() { }

  private getApiUrl(): string {
    return `${this.appConfig.API_URL}/rankings`;
  }

  getRankings(): Observable<RankingModel[]> {
    const url = `${this.getApiUrl()}`;
    return this.http.get<RankingModel[]>(url).pipe(
      catchError(this.handleError)
    );
  }

  create(request: RankingRequest): Observable<RankingModel> {
    const url = `${this.getApiUrl()}`;
    return this.http.post<RankingModel>(url, request).pipe(
      catchError(this.handleError)
    );
  }

  createSnapshot() {
    const url = `${this.getApiUrl()}/snapshot`;
    return this.http.post<RankingModel>(url, null).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: any) {
    console.error('[RankingsService] error:', error);
    return throwError(() => error);
  }

}
