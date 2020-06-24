import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RentalsRoutingModule } from './rentals-routing.module';
import { RentalsListComponent } from './rentals-list/rentals-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [RentalsListComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RentalsRoutingModule
  ]
})
export class RentalsModule { }
