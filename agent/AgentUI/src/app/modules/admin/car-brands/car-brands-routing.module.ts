import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CarBrandsComponent } from './car-brands.component';

const routes: Routes = [{ path: '', component: CarBrandsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarBrandsRoutingModule { }
