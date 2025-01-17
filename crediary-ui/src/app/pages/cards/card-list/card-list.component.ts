import { Component, OnDestroy, OnInit, signal } from "@angular/core";
import { CreditCardComponent } from "../../../shared/credit-card/credit-card.component";
import { Card } from "../../../shared/interfaces/Card";
import { CardService } from "../services/card.service";
import { Subject, takeUntil } from "rxjs";
import { RouterModule } from "@angular/router";

@Component({
  selector: "app-card-list",
  imports: [CreditCardComponent, RouterModule],
  templateUrl: "./card-list.component.html",
  styleUrl: "./card-list.component.scss",
})
export class CardListComponent implements OnInit, OnDestroy {
  cards = signal<Card[]>([]);
  private destroy$ = new Subject<void>();

  constructor(private cardService: CardService) {}

  ngOnInit(): void {
    this.cardService
      .getCards()
      .pipe(takeUntil(this.destroy$))
      .subscribe((cards) => {
        this.cards.set(cards);
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
