import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CarsRoutingModule } from './cars-routing.module';
import { CarsComponent } from './cars.component';



@NgModule({
  declarations: [CarsComponent],
  imports: [
    CommonModule,
    FormsModule,
    CarsRoutingModule
  ]
})
export class CarsModule { }
