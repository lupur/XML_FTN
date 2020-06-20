export interface CarCategory {
	id: number;
	name: string;
	rentalValue: number;
}

export interface CarCategoryVm {
	carCategories: CarCategory[];
}