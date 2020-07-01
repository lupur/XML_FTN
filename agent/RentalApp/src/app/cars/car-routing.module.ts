import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CarListComponent } from './car-list/car-list.component';
import { CarComponent } from './car/car.component';
import { CarAddComponent } from './car-add/car-add.component';


const routes: Routes = [
  {
    path: '',
    component: CarComponent,
    children: [
      {
        path: '',
        component: CarListComponent
      },
      {
        path: 'add',
        component: CarAddComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarRoutingModule { }
