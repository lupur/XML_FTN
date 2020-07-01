import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { CarAddComponent } from './car-add/car-add.component';
import { CarListComponent } from './car-list/car-list.component';
import { CarRoutingModule } from './car-routing.module';
import { CarComponent } from './car/car.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    CarAddComponent,
    CarListComponent,
    CarComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CarRoutingModule
  ]
})
export class CarModule { }
