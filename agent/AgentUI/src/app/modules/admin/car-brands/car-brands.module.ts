import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CarBrandsRoutingModule } from './car-brands-routing.module';
import { CarBrandsListComponent } from './car-brands-list/car-brands-list.component';
import { CarBrandsDetailsComponent } from './car-brands-details/car-brands-details.component';
import { CarBrandsAddComponent } from './car-brands-add/car-brands-add.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    CarBrandsListComponent,
    CarBrandsDetailsComponent,
    CarBrandsAddComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CarBrandsRoutingModule
  ]
})
export class CarBrandsModule { }
