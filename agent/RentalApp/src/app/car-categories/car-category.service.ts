import { environment } from '@env/environment';
import { CarCategoryVm, CarCategory } from './car-category';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CarCategoryService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<CarCategoryVm>(`${environment.apiUrl}/carcategories`);
  }

  get(id: number) {
    return this.http.get<CarCategory>(`${environment.apiUrl}/carcategories/${id}`);
  }

  create(carCategory: CarCategory) {
    return this.http.post(`${environment.apiUrl}/carcategories`, carCategory);
  }

  delete(id: number) {
    return this.http.delete(`${environment.apiUrl}/carcategories/${id}`);
  }

  update(id: number, carCategory: CarCategory) {
    return this.http.put(`${environment.apiUrl}/carcategories/${id}`, carCategory);
  }
}
