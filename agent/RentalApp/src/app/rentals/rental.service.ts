import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
import { RentalBundleVm, RentalVm } from './rental';

@Injectable({
  providedIn: 'root'
})
export class RentalService {


  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<RentalBundleVm>(`${environment.apiUrl}/rentals`);
  }

  getRentalsForBundle(id: number) {
    return this.http.get<RentalVm>(`${environment.apiUrl}/rentals/bundle/${id}`);
  }
}
