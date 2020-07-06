import { Role } from './role';

export interface UserVm {
	users: User[];
}

export interface User {
	id: number;
	firstName: string;
	lastName: string;
	username: string;
	password: string;
	email: string;
	token?: string;
	status: AccountStatus;
	roles: Role[];
}

export enum AccountStatus {
	PENDING = 'Pending',
	ACTIVE = 'Active',
	BLOCKED = 'Blocked'
}