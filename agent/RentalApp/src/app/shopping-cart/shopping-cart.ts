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
	shoppingCartId: number;
	status?: OrderStatus;
}

export enum OrderStatus {
	PENDING = 'Pending',
	ACTIVE = 'Ordered'
}
