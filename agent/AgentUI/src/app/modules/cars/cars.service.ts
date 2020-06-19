import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CarVm } from '@app/models/car';
import { environment } from '@env/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class CarsService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<CarVm>(`${environment.apiUrl}/cars`);
  }
}
