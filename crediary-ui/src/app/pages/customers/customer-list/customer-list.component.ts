import { Component, OnDestroy, OnInit, signal } from '@angular/core';
import { Subject} from 'rxjs';
import { Customer } from '../../../shared/interfaces/Customer';
import { CustomerService } from '../services/customer.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-customer-list',
  imports: [RouterModule],
  templateUrl: './customer-list.component.html',
  styleUrl: './customer-list.component.scss'
})
export class CustomerListComponent implements OnInit, OnDestroy {

  customers = signal<Customer[]>([]);
  private destroy$ = new Subject<void>();
  
  constructor(private customerService: CustomerService) {
    
  }

  ngOnInit(): void {
    this.customerService.getAllCustomers().subscribe(customers => {
      this.customers.set(customers);
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

}
