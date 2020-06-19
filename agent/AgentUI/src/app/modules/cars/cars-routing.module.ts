import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CarsComponent } from './cars.component';
import { LayoutComponent } from '@app/shared/components/layout/layout.component';

const routes: Routes = [
  { path: '', component: CarsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarsRoutingModule { }
