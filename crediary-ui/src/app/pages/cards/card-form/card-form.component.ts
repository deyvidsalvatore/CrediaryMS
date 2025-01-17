import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { CardBrand } from '../../../shared/enums/CardBrand';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-card-form',
  imports: [ReactiveFormsModule, RouterModule],
  templateUrl: './card-form.component.html',
  styleUrl: './card-form.component.scss'
})
export class CardFormComponent implements OnInit, OnDestroy {

  cardForm = new FormGroup<any>({});
  cardBrands = Object.values(CardBrand);
  private fb = inject(FormBuilder);

  private destroy$ = new Subject<void>();

  ngOnInit(): void {
    this.initializeForm();
  }
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  initializeForm(): void {
    this.cardForm = this.fb.group({
      name: ['', Validators.required],
      brand: ['', Validators.required],
      income: [0, Validators.required],
      basicLimit: [0, Validators.required]
    });
  }

  onSubmit(): void {
    console.log(this.cardForm.getRawValue());
  }
}
