import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CarModelsRoutingModule } from './car-models-routing.module';
import { CarModelsListComponent } from './car-models-list/car-models-list.component';


@NgModule({
  declarations: [CarModelsListComponent],
  imports: [
    CommonModule,
    CarModelsRoutingModule
  ]
})
export class CarModelsModule { }
