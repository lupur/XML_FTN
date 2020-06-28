import { CategoryDetailComponent } from './manage-categories/category-detail/category-detail.component';
import { ManageModelsComponent } from './manage-models/manage-models.component';
import { ModelAddComponent } from './manage-models/model-add/model-add.component';
import { ModelDetailComponent } from './manage-models/model-detail/model-detail.component';
import { ModelListComponent } from './manage-models/model-list/model-list.component';
import { CategoryListComponent } from './manage-categories/category-list/category-list.component';
import { CategoryAddComponent } from './manage-categories/category-add/category-add.component';
import { BrandDetailComponent } from './manage-brands/brand-detail/brand-detail.component';
import { BrandListComponent } from './manage-brands/brand-list/brand-list.component';
import { BrandAddComponent } from './manage-brands/brand-add/brand-add.component';
import { ManageCategoriesComponent } from './manage-categories/manage-categories.component';
import { ManageBrandsComponent } from './manage-brands/manage-brands.component';
import { AdminRootComponent } from './admin-root/admin-root.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { componentFactoryName } from '@angular/compiler';


const routes: Routes = [
  {
    path: '',
    component: AdminRootComponent,
    children: [
      {
        path: 'brands',
        component: ManageBrandsComponent,
        children: [
          {
            path: 'add',
            component: BrandAddComponent
          },
          {
            path: 'detail/:name',
            component: BrandDetailComponent
          },
          {
            path: '',
            component: BrandListComponent
          }
        ]
      },
      {
        path: 'models',
        component: ManageModelsComponent,
        children: [
          {
            path: 'add',
            component: ModelAddComponent
          },
          {
            path: 'detail/:name',
            component: ModelDetailComponent
          },
          {
            path: '',
            component: ModelListComponent
          }
        ]
      },
      {
        path: 'categories',
        component: ManageCategoriesComponent,
        children: [
          {
            path: 'add',
            component: CategoryAddComponent
          },
          {
            path: 'detail/:id',
            component: CategoryDetailComponent
          },
          {
            path: '',
            component: CategoryListComponent
          }
        ]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
