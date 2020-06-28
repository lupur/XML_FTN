import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RentalRoutingModule } from './rental-routing.module';
import { RentalComponent } from './rental/rental.component';
import { RentalListComponent } from './rental-list/rental-list.component';


@NgModule({
  declarations: [
    RentalComponent,
    RentalListComponent
  ],
  imports: [
    CommonModule,
    RentalRoutingModule
  ]
})
export class RentalModule { }
