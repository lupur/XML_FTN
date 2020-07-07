import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
import { AccountStatus, User, UserVm } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<UserVm>(`${environment.apiUrl}/users`);
  }

  getById(id: number) {
    return this.http.get<User>(`${environment.apiUrl}/users/${id}`);
  }

  activate(id: number) {
    return this.http.put(`${environment.apiUrl}/users/${id}/activate`, { id, status: AccountStatus.ACTIVE });
  }

  block(id: number) {
    return this.http.put(`${environment.apiUrl}/users/${id}/block`, { id, status: AccountStatus.BLOCKED });
  }

  invite(user: User) {
    return this.http.post(`${environment.apiUrl}/users/invite`, user);
  }

  delete(id: number) {
    return this.http.delete(`${environment.apiUrl}/users/${id}`);
  }
}
