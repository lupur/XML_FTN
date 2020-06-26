import { ManageCategoriesComponent } from './manage-categories/manage-categories.component';
import { ManageBrandsComponent } from './manage-brands/manage-brands.component';
import { AdminRootComponent } from './admin-root/admin-root.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: '',
    component: AdminRootComponent,
    children: [
      {
        path: 'brands',
        component: ManageBrandsComponent
      },
      {
        path: 'categories',
        component: ManageCategoriesComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
