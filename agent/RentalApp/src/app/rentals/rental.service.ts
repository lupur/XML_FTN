import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
import { RentalBundle, RentalBundleVm, RentalVm } from './rental';

@Injectable({
  providedIn: 'root'
})
export class RentalService {


  constructor(private http: HttpClient) { }

  getAllBundles() {
    return this.http.get<RentalBundleVm>(`${environment.apiUrl}/bundles`);
  }

  getBundleById(id: number) {
    return this.http.get<RentalBundle>(`${environment.apiUrl}/bundles/${id}`);
  }

  getRentalsForBundleId(id: number) {
    return this.http.get<RentalVm>(`${environment.apiUrl}/rentals/bundle/${id}`);
  }
}
