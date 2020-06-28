import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RentalComponent } from './rental/rental.component';
import { RentalListComponent } from './rental-list/rental-list.component';

const routes: Routes = [
  {
    path: '',
    component: RentalComponent,
    children: [
      {
        path: '',
        component: RentalListComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RentalRoutingModule { }
