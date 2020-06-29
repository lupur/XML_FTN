import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent, AdminRootComponent } from './admin-root';
import { BrandAddComponent, BrandDetailComponent, BrandListComponent, ManageBrandsComponent } from './manage-brands';
import { CategoryAddComponent, CategoryDetailComponent, CategoryListComponent, ManageCategoriesComponent } from './manage-categories';
import { ManageModelsComponent, ModelAddComponent, ModelDetailComponent, ModelListComponent } from './manage-models';


const routes: Routes = [
  {
    path: '',
    component: AdminRootComponent,
    children: [
      {
        path: 'dashboard',
        component: AdminDashboardComponent
      },
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
