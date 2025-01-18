import { Component, inject, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from "./shared/header/header.component";
import { FooterComponent } from "./shared/footer/footer.component";
import { AuthService } from './shared/services/auth.service';

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
export class AppComponent implements OnInit {
  private authService = inject(AuthService);

  ngOnInit(): void {

    const storedToken = this.authService.getStoredToken();

    if (storedToken) {
      console.log('Already has a token');
    } else {
      this.authService.getAccessToken().subscribe({
        next: () => {
          console.log('Setting a new token');
        },
        error: (error) => {
          console.error('Error getting access token:', error);
        }
      })
    }
  }
}
