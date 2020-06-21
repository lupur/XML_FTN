import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CarBrandsListComponent } from './car-brands-list/car-brands-list.component';
import { CarBrandsDetailsComponent } from './car-brands-details/car-brands-details.component';


const routes: Routes = [
  {
    path: '',
    component: CarBrandsListComponent,
  },
  {
    path: ':carBrandName/carmodels',
    component: CarBrandsDetailsComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarBrandsRoutingModule { }
