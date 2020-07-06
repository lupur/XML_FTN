import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CarBrandsListComponent } from './car-brands/car-brands-list/car-brands-list.component';
import { CarModelsListComponent } from './car-models/car-models-list/car-models-list.component';


@NgModule({
  declarations: [
    AdminComponent,
    CarBrandsListComponent,
    CarModelsListComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
