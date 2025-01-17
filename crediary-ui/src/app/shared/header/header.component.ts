import { NgClass } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule, NgClass],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  links = [
    { name: 'Home', url: '/' },
    { name: 'Customers', url: '/customers' },
    { name: 'Cards', url: '/cards' },
    { name: 'Credit Appraiser', url: '/credit-appraiser' },
    { name: 'About', url: '/about'}
  ];

  iconClass(linkName: string): string {
    const iconMap: { [key: string]: string } = {
      'Home': 'bi-house-door',
      'Customers': 'bi-person-lines-fill',
      'Cards': 'bi-credit-card',
      'Credit Appraiser': 'bi-graph-up',
      'About': 'bi-info-circle'
    };
    
    return iconMap[linkName] || 'bi-question-circle';
  }
}
