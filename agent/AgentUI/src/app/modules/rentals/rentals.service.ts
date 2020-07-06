import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RentalVm } from '@app/models/rental';
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root'
})
export class RentalsService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<RentalVm>(`${environment.apiUrl}/rentals`);
  }
}
