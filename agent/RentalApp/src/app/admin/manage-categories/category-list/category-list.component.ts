import { AlertService } from '@app/shared/alert/alert.service';
import { first } from 'rxjs/operators';
import { CarCategoryService } from './../../../car-categories/car-category.service';
import { CarCategory } from './../../../car-categories/car-category';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {
  categories: CarCategory[];

  deleting = false;

  constructor(
    private carCategoryService: CarCategoryService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.carCategoryService.getAll()
      .pipe(first())
      .subscribe(carCategoryVm => this.categories = carCategoryVm.carCategories);
  }

  delete(id: number) {
    this.deleting = true;
    this.carCategoryService.delete(id)
      .pipe(first())
      .subscribe(() => {
        this.categories = this.categories.filter(x => x.id !== id);
        this.alertService.success('Category deleted successfully', {
          autoClose: true
        });
        this.deleting = false;
      });
  }
}
