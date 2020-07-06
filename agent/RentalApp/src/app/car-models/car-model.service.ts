import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
import { CarModel, CarModelVm } from './car-model';

@Injectable({
  providedIn: 'root'
})
export class CarModelService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<CarModelVm>(`${environment.apiUrl}/carmodels`);
  }

  get(name: string) {
    return this.http.get<CarModel>(`${environment.apiUrl}/carmodels/${name}`);
  }

  getByBrand(brandName: string) {
    return this.http.get<CarModelVm>(`${environment.apiUrl}/carmodels/brand/${brandName}`);
  }

  create(carModel: CarModel) {
    return this.http.post(`${environment.apiUrl}/carmodels`, carModel);
  }

  delete(name: string) {
    return this.http.delete(`${environment.apiUrl}/carmodels/${name}`);
  }

}
