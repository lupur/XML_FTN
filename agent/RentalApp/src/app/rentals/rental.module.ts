import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RentalRoutingModule } from './rental-routing.module';
import { RentalComponent } from './rental/rental.component';
import { RentalListComponent } from './rental-list/rental-list.component';
import { RentalDetailComponent } from './rental-detail/rental-detail.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    RentalComponent,
    RentalListComponent,
    RentalDetailComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RentalRoutingModule
  ]
})
export class RentalModule { }
