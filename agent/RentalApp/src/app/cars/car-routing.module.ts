import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarAddComponent } from './car-add/car-add.component';
import { CarDetailComponent } from './car-detail/car-detail.component';
import { CarListComponent } from './car-list/car-list.component';
import { CarReviewComponent } from './car-review/car-review.component';
import { CarComponent } from './car/car.component';


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
      },
      {
        path: 'detail/:id',
        component: CarDetailComponent,
        children: [
          {
            path: '',
            component: CarReviewComponent
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
export class CarRoutingModule { }
