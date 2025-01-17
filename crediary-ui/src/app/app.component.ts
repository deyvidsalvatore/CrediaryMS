import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from "./shared/header/header.component";
import { FooterComponent } from "./shared/footer/footer.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent, FooterComponent],
  template: `
    <app-header></app-header>
      <div class="flex-grow-1">
        <router-outlet></router-outlet>
      </div>
    <app-footer></app-footer>
  `,
  styles: [],
})
export class AppComponent {
  title = 'crediary-ui';
}
