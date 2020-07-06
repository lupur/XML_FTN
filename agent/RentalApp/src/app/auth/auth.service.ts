import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '@env/environment';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { RoleType } from './role';
import { AccountStatus, User, UserVm } from './user';



@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private userSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(
    private router: Router,
    private http: HttpClient
  ) {
    this.userSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('user')));
    this.currentUser = this.userSubject.asObservable();
  }

  public get userValue() {
    return this.userSubject.value;
  }

  isAuthorized() {
    return !!this.userValue;
  }

  hasRole(role: RoleType) {
    return this.isAuthorized() && this.userValue.roles.some(r => r.name === role);
  }

  login(usernameOrEmail: string, password: string) {
    return this.http.post<User>(`${environment.apiUrl}/users/login`, { usernameOrEmail, password })
      .pipe(
        map(user => {
          localStorage.setItem('user', JSON.stringify(user));
          this.userSubject.next(user);
          return user;
        }));
  }

  logout() {
    localStorage.removeItem('user');
    this.userSubject.next(null);
    this.router.navigate(['/auth/login']);
  }

  register(user: User) {
    return this.http.post(`${environment.apiUrl}/users/register`, user);
  }

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

  delete(id: number) {
    return this.http.delete(`${environment.apiUrl}/users/${id}`);
  }
}
