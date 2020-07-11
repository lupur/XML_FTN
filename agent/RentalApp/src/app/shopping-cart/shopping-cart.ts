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

export interface UpdateShoppingCart {
	id: number;
}

export enum OrderStatus {
	PENDING = 'Pending',
	ORDERED = 'Ordered'
}
