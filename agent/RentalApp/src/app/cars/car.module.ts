import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { CarAddComponent } from './car-add/car-add.component';
import { CarListComponent } from './car-list/car-list.component';
import { CarRoutingModule } from './car-routing.module';
import { CarComponent } from './car/car.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CarDetailComponent } from './car-detail/car-detail.component';
import { CarReviewComponent } from './car-review/car-review.component';


@NgModule({
  declarations: [
    CarAddComponent,
    CarListComponent,
    CarComponent,
    CarDetailComponent,
    CarReviewComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CarRoutingModule
  ]
})
export class CarModule { }
