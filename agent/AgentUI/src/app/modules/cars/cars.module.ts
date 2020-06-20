import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CarsRoutingModule } from './cars-routing.module';
import { CarsListComponent } from './cars-list/cars-list.component';
import { CarsAddComponent } from './cars-add/cars-add.component';
import { CarsEditComponent } from './cars-edit/cars-edit.component';
import { CarsDetailsComponent } from './cars-details/cars-details.component';


@NgModule({
  declarations: [
    CarsListComponent,
    CarsAddComponent,
    CarsEditComponent,
    CarsDetailsComponent,
    CarsDetailsComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CarsRoutingModule
  ]
})
export class CarsModule { }
