import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
import { Review, ReviewStatus, ReviewVm } from './review';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<ReviewVm>(`${environment.apiUrl}/reviews`);
  }

  getReviewsForCar(id: number, status: ReviewStatus) {
    let params = new HttpParams()
      .set("status", status);
    return this.http.get<ReviewVm>(`${environment.apiUrl}/reviews/car/${id}`, { params: params })
  }

  create(review: Review) {
    return this.http.post(`${environment.apiUrl}/reviews`, review);
  }

  update(id: number, status: ReviewStatus) {
    return this.http.put(`${environment.apiUrl}/reviews/${id}`, { id, status })
  }
}
