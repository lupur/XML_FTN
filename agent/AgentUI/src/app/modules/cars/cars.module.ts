import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CarsAddComponent } from './cars-add/cars-add.component';
import { CarsRoutingModule } from './cars-routing.module';
import { CarsComponent } from './cars.component';


@NgModule({
  declarations: [
    CarsComponent,
    CarsAddComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CarsRoutingModule
  ]
})
export class CarsModule { }
