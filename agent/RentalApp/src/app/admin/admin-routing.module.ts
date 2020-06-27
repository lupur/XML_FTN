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
            component: BrandDetailComponent,
            children: [
              {
                path: '',
                component: ModelListComponent
              },
              {
                path: 'add',
                component: ModelAddComponent
              },
              {
                path: ':name',
                component: ModelDetailComponent
              }
            ]
          },
          {
            path: '',
            component: BrandListComponent
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
