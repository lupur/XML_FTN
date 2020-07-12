import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  

  constructor(private http: HttpClient) { }

  getMileageReport() {
    let headers = new HttpHeaders();
    headers = headers.set('Accept', 'application/pdf');
    return this.http.get<Blob>(`${environment.apiUrl}/dashboard/mileage-report`, { headers: headers, responseType: 'blob' as 'json' });
  }

  getRatingReport() {
    let headers = new HttpHeaders();
    headers = headers.set('Accept', 'application/pdf');
    return this.http.get<Blob>(`${environment.apiUrl}/dashboard/rating-report`, { headers: headers, responseType: 'blob' as 'json' })
  }

  getCommentReport() {
    let headers = new HttpHeaders();
    headers = headers.set('Accept', 'application/pdf');
    return this.http.get<Blob>(`${environment.apiUrl}/dashboard/comment-report`, { headers: headers, responseType: 'blob' as 'json' })
  }
}
