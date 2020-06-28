export interface CarCategoryVm {
	carCategories: CarCategory[];
}

export interface CarCategory {
	id: number;
	name: string;
	rentalValue: number;
	isDeleting: boolean;
}
