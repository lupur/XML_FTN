import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CarsComponent } from './cars.component';
import { LayoutComponent } from '@app/shared/components/layout/layout.component';
import { CarsAddComponent } from './cars-add/cars-add.component';

const routes: Routes = [
  { path: '', component: CarsComponent },
  { path: 'add', component: CarsAddComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarsRoutingModule { }
