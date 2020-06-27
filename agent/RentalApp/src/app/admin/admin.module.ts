import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminRootComponent } from './admin-root/admin-root.component';
import { ManageBrandsComponent } from './manage-brands/manage-brands.component';
import { ManageCategoriesComponent } from './manage-categories/manage-categories.component';
import { BrandListComponent } from './manage-brands/brand-list/brand-list.component';
import { BrandDetailComponent } from './manage-brands/brand-detail/brand-detail.component';
import { BrandAddComponent } from './manage-brands/brand-add/brand-add.component';


@NgModule({
  declarations: [
    AdminRootComponent,
    ManageBrandsComponent,
    ManageCategoriesComponent,
    BrandListComponent,
    BrandDetailComponent,
    BrandAddComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
