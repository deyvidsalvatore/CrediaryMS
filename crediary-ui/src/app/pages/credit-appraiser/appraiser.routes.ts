import { Routes } from "@angular/router";
import { AppraiserSituationComponent } from "./appraiser-situation/appraiser-situation.component";
import { AppraiserMenuComponent } from "./appraiser-menu/appraiser-menu.component";
import { AppraiserAvaliationComponent } from "./appraiser-avaliation/appraiser-avaliation.component";
import { AppraiserFormComponent } from "./appraiser-form/appraiser-form.component";

export const appraiserRoutes: Routes = [
  {
    path: '',
    component: AppraiserMenuComponent
  },
  {
    path: 'situation',
    component: AppraiserSituationComponent
  },
  {
    path: 'avaliation',
    component: AppraiserAvaliationComponent
  },
  {
    path: 'card',
    component: AppraiserFormComponent
  }
];