import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DemoComponent } from './demo.component';
import { DemoDetailsComponent } from './demo-details/demo-details.component';
import { DemoAddComponent } from './demo-add/demo-add.component';

const routes: Routes = [
  { path: '', component: DemoComponent },
  { path: 'add', component: DemoAddComponent },
  { path: 'details/:id', component: DemoDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DemoRoutingModule { }
