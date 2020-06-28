import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CarModel } from '@app/car-models/car-model';
import { CarModelService } from '@app/car-models/car-model.service';
import { AlertService } from '@app/shared/alert/alert.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-model-list',
  templateUrl: './model-list.component.html',
  styleUrls: ['./model-list.component.css']
})
export class ModelListComponent implements OnInit {
  carModels: CarModel[];
  selectedCarBrand: string;

  constructor(
    private route: ActivatedRoute,
    private carModelService: CarModelService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params =>
      this.selectedCarBrand = params.get('name')
    );

    if (this.selectedCarBrand) {
      this.getCarModelsByBrand(this.selectedCarBrand);
    } else {
      this.getCarModels();
    }
  }

  delete(name: string) {
    const carModel = this.carModels.find(x => x.name == name);
    carModel.isDeleting = true;

    this.carModelService.delete(name)
      .pipe(first())
      .subscribe(() => {
        this.carModels = this.carModels.filter(x => x.name !== name);
        this.alertService.success('Model deleted successfully', {
          autoClose: true
        });
      });
  }

  private getCarModelsByBrand(brandName: string) {
    this.carModelService.getByBrand(brandName)
      .pipe(first())
      .subscribe(carModelVm => this.carModels = carModelVm.carModels);
  }

  private getCarModels() {
    this.carModelService.getAll()
      .pipe(first())
      .subscribe(carModelVm => this.carModels = carModelVm.carModels);
  }

}
