import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CarBrandsRoutingModule } from './car-brands-routing.module';
import { CarBrandsListComponent } from './car-brands-list/car-brands-list.component';
import { CarBrandsDetailsComponent } from './car-brands-details/car-brands-details.component';


@NgModule({
  declarations: [CarBrandsListComponent, CarBrandsDetailsComponent],
  imports: [
    CommonModule,
    CarBrandsRoutingModule
  ]
})
export class CarBrandsModule { }
