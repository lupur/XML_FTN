import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminDashboardComponent, AdminRootComponent } from './admin-root';
import { AdminRoutingModule } from './admin-routing.module';
import { BrandAddComponent, BrandDetailComponent, BrandListComponent, ManageBrandsComponent } from './manage-brands';
import { CategoryAddComponent, CategoryDetailComponent, CategoryListComponent, ManageCategoriesComponent } from './manage-categories';
import { ManageModelsComponent, ModelAddComponent, ModelDetailComponent, ModelListComponent } from './manage-models';
import { ManageReviewsComponent } from './manage-reviews/manage-reviews.component';
import { ReviewListComponent } from './manage-reviews/review-list/review-list.component';


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
    ManageReviewsComponent,
    ReviewListComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
