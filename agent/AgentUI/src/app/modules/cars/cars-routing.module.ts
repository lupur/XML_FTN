import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarsAddComponent } from './cars-add/cars-add.component';
import { CarsDetailsComponent } from './cars-details/cars-details.component';
import { CarsListComponent } from './cars-list/cars-list.component';


const routes: Routes = [
  {
    path: '',
    component: CarsListComponent
  },
  {
    path: 'add',
    component: CarsAddComponent
  },
  {
    path: 'details/:id',
    component: CarsDetailsComponent
  },
  {
    path: 'edit/:id',
    component: CarsAddComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarsRoutingModule { }
