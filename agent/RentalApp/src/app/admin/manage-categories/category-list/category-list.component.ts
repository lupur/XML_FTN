import { Component, OnInit } from '@angular/core';
import { CarCategory } from '@app/car-categories/car-category';
import { CarCategoryService } from '@app/car-categories/car-category.service';
import { AlertService } from '@app/shared/alert/alert.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {
  categories: CarCategory[];

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
    const category = this.categories.find(x => x.id == id);
    category.isDeleting = true;

    this.carCategoryService.delete(id)
      .pipe(first())
      .subscribe(() => {
        this.categories = this.categories.filter(x => x.id !== id);
        this.alertService.success('Category deleted successfully', {
          autoClose: true
        });
      });
  }
}
