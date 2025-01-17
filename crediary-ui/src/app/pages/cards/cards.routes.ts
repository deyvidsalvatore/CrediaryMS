import { Routes } from "@angular/router";
import { CardListComponent } from "./card-list/card-list.component";
import { CardFormComponent } from "./card-form/card-form.component";

export const cardRoutes: Routes = [
  {
    path: '',
    component: CardListComponent
  },
  {
    path: 'new',
    component: CardFormComponent
  }
];