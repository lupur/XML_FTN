import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { CarAddComponent } from './car-add/car-add.component';
import { CarListComponent } from './car-list/car-list.component';
import { CarRoutingModule } from './car-routing.module';
import { CarComponent } from './car/car.component';


@NgModule({
  declarations: [
    CarAddComponent,
    CarListComponent,
    CarComponent,
  ],
  imports: [
    CommonModule,
    CarRoutingModule
  ]
})
export class CarModule { }
