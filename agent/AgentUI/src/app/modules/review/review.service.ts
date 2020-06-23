import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@env/environment';
import { Review } from '@app/models/car';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient) { }

  create(review: Review) {
    return this.http.post(`${environment.apiUrl}/reviews`, review);
  }
}
