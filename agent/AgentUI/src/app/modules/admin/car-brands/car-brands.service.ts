import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CarBrand, CarBrandVm } from '@app/models/car-brand';
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root'
})
export class CarBrandsService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<CarBrandVm>(`${environment.apiUrl}/carbrands`)
  }

  getById(carBrandName: string){
    return this.http.get<CarBrand>(`${environment.apiUrl}/carbrands/${carBrandName}`);
  }
  
  create(carBrand: CarBrand){
    this.http.post(`${environment.apiUrl}/carbrands`, carBrand);
  }
  
}
