import { Role } from './role';

export interface User {
	id: number;
	firstName: string;
	lastName: string;
	username: string;
	password: string;
	email: string;
	roles: Role[];
	token?: string;
}

export interface UserVm {
	users: User[];
}
