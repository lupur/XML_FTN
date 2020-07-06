import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
import { Car, CarVm } from './car';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<CarVm>(`${environment.apiUrl}/cars`);
  }

  get(id: number) {
    return this.http.get<Car>(`${environment.apiUrl}/cars/${id}`);
  }

  create(car: Car) {
    return this.http.post(`${environment.apiUrl}/cars`, car);
  }

  delete(id: number) {
    return this.http.delete(`${environment.apiUrl}/cars/${id}`);
  }
}
