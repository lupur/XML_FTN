import { Directive, Input, OnInit, TemplateRef, ViewContainerRef } from '@angular/core';

import { AuthService } from '@app/auth/auth.service';
import { RoleType } from '@app/roles/role';


@Directive({ selector: '[appUserRole]' })
export class UserRoleDirective implements OnInit {
  userRoles: RoleType[];

  constructor(
    private templateRef: TemplateRef<any>,
    private authService: AuthService,
    private viewContainer: ViewContainerRef
  ) { }

  @Input()
  set appUserRole(roles: RoleType[]) {
    if (!roles || !roles.length) {
      throw new Error('Roles value is empty or missed');
    }
    this.userRoles = roles;
  }

  ngOnInit() {
    let hasAccess = false;
    if (this.authService.isAuthorized() && this.userRoles) {
      hasAccess = this.userRoles.some(r => this.authService.hasRole(r));
    }
    if (hasAccess) {
      this.viewContainer.createEmbeddedView(this.templateRef);
    } else {
      this.viewContainer.clear();
    }
  }
}