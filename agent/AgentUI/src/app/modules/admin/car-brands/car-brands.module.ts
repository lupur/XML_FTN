import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CarBrandsRoutingModule } from './car-brands-routing.module';
import { CarBrandsComponent } from './car-brands.component';


@NgModule({
  declarations: [CarBrandsComponent],
  imports: [
    CommonModule,
    CarBrandsRoutingModule
  ]
})
export class CarBrandsModule { }
