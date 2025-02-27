import { Injectable } from "@angular/core";
import { Card } from "../../../shared/interfaces/Card";
import { catchError, Observable, take, throwError } from "rxjs";
import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
} from "@angular/common/http";

@Injectable({
  providedIn: "root",
})
export class CardService {
  private readonly baseUrl = "data/cards.json";

  constructor(private http: HttpClient) {}

  getCards(): Observable<Card[]> {
    return this.http
      .get<Card[]>(this.baseUrl)
      .pipe(take(1), catchError(this.handleError));
  }

  getCardsByIncome(income: number): Observable<Card[]> {
    const params = new HttpParams().set("income", income);
    return this.http
      .get<Card[]>(this.baseUrl, { params: params })
      .pipe(take(1), catchError(this.handleError));
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
