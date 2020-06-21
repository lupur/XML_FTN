import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './modules/auth/auth.guard';
import { RoleType } from './models/role';


const routes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: 'auth',
    loadChildren: () => import('./modules/auth/auth.module').then(m => m.AuthModule)
  },
  {
    path: 'home',
    loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule)
  },
  {
    path: 'cars',
    loadChildren: () => import('./modules/cars/cars.module').then(m => m.CarsModule),
    canActivate: [AuthGuard],
    data: { roles: [RoleType.Administrator, RoleType.Agent] }
  },
  {
    path: 'admin',
    loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule),
    canActivate: [AuthGuard],
    data: { roles: [RoleType.Administrator] }
  },
  { path: 'demo', loadChildren: () => import('./modules/demo/demo.module').then(m => m.DemoModule) },
  {
    // fallback route
    path: '**',
    redirectTo: '',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
