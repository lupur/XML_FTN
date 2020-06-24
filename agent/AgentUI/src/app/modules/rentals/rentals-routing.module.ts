import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RentalsListComponent } from './rentals-list/rentals-list.component';

const routes: Routes = [
  {
    path: '',
    component: RentalsListComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RentalsRoutingModule { }
