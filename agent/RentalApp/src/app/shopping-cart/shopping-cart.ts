export interface ShoppingCart {
	id?: number;
	userId: number;
	userFirstName?: string;
	numberOfItems?: number;
	items?: ShoppingCartItem[];
}

export interface ShoppingCartItem {
	id?: number;
	carId: number;
	ownerId: number;
	shoppingCartId: number;
	status?: OrderStatus;
	isBundle?: boolean;
}

export enum OrderStatus {
	PENDING = 'Pending',
	ACTIVE = 'Ordered'
}
