import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AdminComponent } from './admin.component';
import { CarBrandsListComponent } from './car-brands/car-brands-list/car-brands-list.component';
import { CarModelsListComponent } from './car-models/car-models-list/car-models-list.component';

const routes: Routes = [
  {
    path: '',
    component: AdminComponent,
    children: [
      {
        path: '',
        children: [
          {
            path: 'carbrands',
            component: CarBrandsListComponent
          },
          {
            path: 'carmodels',
            component: CarModelsListComponent
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
