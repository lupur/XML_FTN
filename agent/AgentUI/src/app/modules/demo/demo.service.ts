import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DemoCarBrand } from './demo-car-brand';
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root'
})
export class DemoService {
  carBrands: DemoCarBrand[];

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<DemoCarBrand[]>(`${environment.apiUrl}/soap/brands`);
  }

  getById(id: number) {
    return this.http.get<DemoCarBrand>(`${environment.apiUrl}/soap/brands/${id}`);
  }

  create(name: any) {
    return this.http.post(`${environment.apiUrl}/soap/brands`, { name: name });
  }
}
