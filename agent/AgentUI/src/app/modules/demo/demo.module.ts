import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DemoRoutingModule } from './demo-routing.module';
import { DemoComponent } from './demo.component';
import { DemoDetailsComponent } from './demo-details/demo-details.component';
import { DemoAddComponent } from './demo-add/demo-add.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [DemoComponent, DemoDetailsComponent, DemoAddComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    DemoRoutingModule
  ]
})
export class DemoModule { }
