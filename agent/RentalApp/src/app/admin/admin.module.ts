import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRootComponent, AdminDashboardComponent } from './admin-root';
import { ManageBrandsComponent, BrandListComponent, BrandDetailComponent, BrandAddComponent } from './manage-brands';
import { ManageCategoriesComponent, CategoryListComponent, CategoryAddComponent, CategoryDetailComponent } from './manage-categories';
import { ManageModelsComponent, ModelAddComponent, ModelListComponent, ModelDetailComponent } from './manage-models';
import { ManageCarsComponent } from './manage-cars';
import { AdminRoutingModule } from './admin-routing.module';
import { CarAddComponent } from './manage-cars/car-add/car-add.component';


@NgModule({
  declarations: [
    AdminRootComponent,
    ManageBrandsComponent,
    ManageCategoriesComponent,
    BrandListComponent,
    BrandDetailComponent,
    BrandAddComponent,
    CategoryListComponent,
    CategoryAddComponent,
    CategoryDetailComponent,
    ManageModelsComponent,
    ModelAddComponent,
    ModelListComponent,
    ModelDetailComponent,
    AdminDashboardComponent,
    ManageCarsComponent,
    CarAddComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
