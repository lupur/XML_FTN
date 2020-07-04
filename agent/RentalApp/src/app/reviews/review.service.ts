import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
import { Review, ReviewStatus, ReviewVm } from './review';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient) { }

  getAll(carId: number, status: ReviewStatus) {
    let params = new HttpParams()
      .set("carId", carId.toString())
      .set("status", status);
    return this.http.get<ReviewVm>(`${environment.apiUrl}/reviews`, { params: params })
  }

  create(review: Review) {
    return this.http.post(`${environment.apiUrl}/reviews`, review);
  }
}
