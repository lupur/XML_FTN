import { environment } from '@env/environment';
import { CarBrand } from './car-brand';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CarBrandService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<unknown[]>(`${environment.apiUrl}/carbrands`);
  }

  get(name: string) {
    return this.http.get<any>(`${environment.apiUrl}/carbrands/${name}`)
  }

  create(carBrand: CarBrand) {
    return this.http.post(`${environment.apiUrl}/carbrands`, carBrand);
  }
}
