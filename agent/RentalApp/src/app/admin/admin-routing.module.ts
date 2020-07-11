import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent, AdminRootComponent } from './admin-root';
import { BrandAddComponent, BrandDetailComponent, BrandListComponent, ManageBrandsComponent } from './manage-brands';
import { CategoryAddComponent, CategoryDetailComponent, CategoryListComponent, ManageCategoriesComponent } from './manage-categories';
import { ManageModelsComponent, ModelAddComponent, ModelListComponent } from './manage-models';
import { ManageReviewsComponent } from './manage-reviews/manage-reviews.component';
import { ReviewListComponent } from './manage-reviews/review-list/review-list.component';
import { ManageUsersComponent } from './manage-users/manage-users.component';
import { UserAddComponent } from './manage-users/user-add/user-add.component';
import { UserDetailComponent } from './manage-users/user-detail/user-detail.component';
import { UserListComponent } from './manage-users/user-list/user-list.component';


const routes: Routes = [
  {
    path: '',
    component: AdminRootComponent,
    children: [
      {
        path: 'dashboard',
        component: AdminDashboardComponent
      },
      {
        path: 'brands',
        component: ManageBrandsComponent,
        children: [
          {
            path: 'add',
            component: BrandAddComponent
          },
          {
            path: 'detail/:name',
            component: BrandDetailComponent
          },
          {
            path: '',
            component: BrandListComponent
          }
        ]
      },
      {
        path: 'models',
        component: ManageModelsComponent,
        children: [
          {
            path: 'add',
            component: ModelAddComponent
          },
          {
            path: '',
            component: ModelListComponent
          }
        ]
      },
      {
        path: 'categories',
        component: ManageCategoriesComponent,
        children: [
          {
            path: 'add',
            component: CategoryAddComponent
          },
          {
            path: 'detail/:id',
            component: CategoryDetailComponent
          },
          {
            path: '',
            component: CategoryListComponent
          }
        ]
      },
      {
        path: 'reviews',
        component: ManageReviewsComponent,
        children: [
          {
            path: '',
            component: ReviewListComponent
          }
        ]
      },
      {
        path: 'users',
        component: ManageUsersComponent,
        children: [
          {
            path: 'add',
            component: UserAddComponent
          },
          {
            path: 'detail/:id',
            component: UserDetailComponent
          },
          {
            path: '',
            component: UserListComponent
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
export class AdminRoutingModule { }
