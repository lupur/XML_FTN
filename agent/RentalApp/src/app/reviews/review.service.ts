import { Injectable } from '@angular/core';
import { Review } from './review';
import { environment } from '@env/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient) { }

  create(review: Review) {
    return this.http.post(`${environment.apiUrl}/reviews`, review);
  }
}
