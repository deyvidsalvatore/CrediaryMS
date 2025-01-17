import { Injectable } from "@angular/core";
import { Customer } from "../../../shared/interfaces/Customer";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { catchError, Observable, take, throwError } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class CustomerService {
  private readonly _baseUrl = "/data/customers.json";

  constructor(private http: HttpClient) {}

  getAllCustomers(): Observable<Customer[]> {
    return this.http
      .get<Customer[]>(this._baseUrl)
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
