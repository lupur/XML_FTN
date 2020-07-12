export interface RoleVm {
	roles: Role[];
}

export interface Role {
	id: number;
	name: RoleType;
}

export interface UserRole {
	userId: number;
	roleId: number;
}

export enum RoleType {
	Admin = 'administrator',
	Agent = 'agent',
	Customer = 'customer'
}