import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CarVm, Car } from '@app/models/car';
import { environment } from '@env/environment.prod';
import { CarCategoryVm } from '@app/models/car-category';

@Injectable({
  providedIn: 'root'
})
export class CarsService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<CarVm>(`${environment.apiUrl}/cars`);
  }

  getCarCategories(){
    return this.http.get<CarCategoryVm>(`${environment.apiUrl}/carCategories`)
  }

  create(car: Car) {
    return this.http.post(`${environment.apiUrl}/cars`, car);
  }
}
