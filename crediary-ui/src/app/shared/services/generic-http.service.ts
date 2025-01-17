import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, take, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GenericHttpService<T> {
  private _baseUrl: string = '';

  constructor(private http: HttpClient) {}

  get(params?: HttpParams, headers?: HttpHeaders): Observable<T[] | never> {
    const options = { params, headers };
    return this.http
      .get<T[]>(this._baseUrl, options)
      .pipe(take(1), catchError(this.handleError));
  }

  post(body: T, headers?: HttpHeaders): Observable<T | never> {
    const options = { headers };
    return this.http
      .post<T>(this._baseUrl, body, options)
      .pipe(take(1), catchError(this.handleError));
  }


  get baseUrl(): string {
    return this._baseUrl;
  }

  set baseUrl(url: string) {
    this._baseUrl = url;
  }

  private handleError(err: HttpErrorResponse): Observable<never> {
    let errorMessage: string;
    if (err.error instanceof ErrorEvent) {
      errorMessage = `An error ocurred: ${err.error.message}`;
    } else {
      errorMessage = `Error ${err.status}: ${err.message}`;
    }
    console.error(err);
    return throwError(() => new Error(errorMessage));
  }

}
