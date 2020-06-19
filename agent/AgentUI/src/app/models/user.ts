export interface User {
	id: number;
	firstName: string;
	lastName: string;
	username: string;
	password: string;
	email: string;
	token?: string;
}

export interface UserVm {
	users: User[];
}
