import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '@app/shared/shared.module';
import { RentalDetailComponent } from './rental-detail/rental-detail.component';
import { RentalListComponent } from './rental-list/rental-list.component';
import { RentalRoutingModule } from './rental-routing.module';
import { RentalComponent } from './rental/rental.component';


@NgModule({
  declarations: [
    RentalComponent,
    RentalListComponent,
    RentalDetailComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    RentalRoutingModule
  ]
})
export class RentalModule { }
