import { Injectable } from '@angular/core';
import { GenericHttpService } from '../../../shared/services/generic-http.service';
import { Card } from '../../../shared/interfaces/Card';
import { Observable } from 'rxjs';
import { HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  private readonly baseUrl = "data/cards.json";

  constructor(private genericHttp: GenericHttpService<Card>) {
    this.genericHttp.baseUrl = this.baseUrl;
  }

  getCards(): Observable<Card[]> {
    return this.genericHttp.get();
  }

  getCardsByIncome(income: number): Observable<Card[]> {
    const params = new HttpParams().set('income', income);
    return this.genericHttp.get(params);
  }
}
