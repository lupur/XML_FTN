import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AdminComponent } from './admin.component';

const routes: Routes = [
  {
    path: '',
    component: AdminComponent
  },
  {
    path: 'carbrands',
    loadChildren: () => import('./car-brands/car-brands.module').then(m => m.CarBrandsModule)
  },
  {
    path: 'carmodels',
    loadChildren: () => import('./car-models/car-models.module').then(m => m.CarModelsModule)
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
