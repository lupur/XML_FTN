import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlertComponent } from './alert/alert.component';
import { UserDirective } from './auth/user.directive';
import { UserRoleDirective } from './auth/user-role.directive';


@NgModule({
  declarations: [
    AlertComponent,
    UserDirective,
    UserRoleDirective
  ],
  imports: [
    CommonModule
  ],
  exports: [
    AlertComponent,
    UserDirective,
    UserRoleDirective
  ]
})
export class SharedModule { }
