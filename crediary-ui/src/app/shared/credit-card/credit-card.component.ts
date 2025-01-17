import { Component, Input } from '@angular/core';
import { Card } from '../interfaces/Card';
import { CardBrand } from '../enums/CardBrand';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-credit-card',
  imports: [NgClass],
  templateUrl: './credit-card.component.html',
  styleUrl: './credit-card.component.scss'
})
export class CreditCardComponent {
  @Input() card: Card = {
    name: "VisaVisitorVI",
    brand: CardBrand.VISA,
    income: 3000,
    basicLimit: 3500
  };

  getCardClass() {
    switch (this.card.brand) {
      case CardBrand.MASTERCARD:
        return 'mastercard';
      case CardBrand.VISA:
        return 'visa';
      case CardBrand.AMERICANEXPRESS:
        return 'americanexpress';
      case CardBrand.DISCOVER:
        return 'discover';
      default:
        return 'visa';
    }
  }

}
