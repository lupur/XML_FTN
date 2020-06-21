export interface Role {
	name: string;
}

export enum RoleType {
	Administrator = 'administrator',
	Agent = 'agent',
	Customer = 'customer'
}