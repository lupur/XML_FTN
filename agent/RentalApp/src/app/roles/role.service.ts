import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Role, RoleVm, UserRole } from '@app/roles/role';
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<RoleVm>(`${environment.apiUrl}/roles`);
  }

  getById(id: number) {
    return this.http.get<Role>(`${environment.apiUrl}/roles/${id}`);
  }

  addUserRole(userRole: UserRole) {
    return this.http.post(`${environment.apiUrl}/userroles`, userRole);
  }
}
