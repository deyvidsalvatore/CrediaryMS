import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-customer-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './customer-form.component.html',
  styleUrl: './customer-form.component.scss'
})
export class CustomerFormComponent implements OnInit, OnDestroy {

  customerForm = new FormGroup<any>({});
  private fb = inject(FormBuilder);
  private destroy$ = new Subject<void>();

  ngOnInit(): void {
    this.customerForm = this.fb.group({
      ssn: ['', Validators.required],
      name: ['', Validators.required],
      age: [0, Validators.required],
    });
    
  }
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  onSubmit(): void {
    console.log(this.customerForm.getRawValue());
  }

}
