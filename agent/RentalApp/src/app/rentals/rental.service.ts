import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
import { Rental, RentalBundle, RentalBundleVm, RentalRequest, RentalResponse, RentalVm, BundleRequest } from './rental';

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

  getRentalById(id: number) {
    return this.http.get<Rental>(`${environment.apiUrl}/rentals/${id}`);
  }

  getRentalsForBundleId(id: number) {
    return this.http.get<RentalVm>(`${environment.apiUrl}/rentals/bundle/${id}`);
  }

  createRentalRequest(request: BundleRequest) {
    return this.http.post(`${environment.apiUrl}/rentals`, request);
  }

  updateRentalRequest(bundleId: number, rentalResponse: RentalResponse) {
    return this.http.put(`${environment.apiUrl}/rentals/${bundleId}`, rentalResponse);
  }
}
