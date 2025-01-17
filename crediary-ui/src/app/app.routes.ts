import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AboutComponent } from './pages/about/about.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'customers',
    loadChildren: () => import('./pages/customers/customer.routing').then((m) => m.customerRoutes)
  },
  {
    path: 'cards',
    loadChildren: () => import('./pages/cards/cards.routes').then((m) => m.cardRoutes)
  },
  {
    path: 'credit-appraiser',
    loadChildren: () => import('./pages/credit-appraiser/appraiser.routes').then((m) => m.appraiserRoutes)
  },
  {
    path: 'about',
    component: AboutComponent
  }
];
