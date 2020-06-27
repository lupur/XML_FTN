export interface CarBrandVm {
	carBrands: CarBrand[];
}

export interface CarBrand {
	name: string;
	carModels: CarModel[];
}

export interface CarModel {
	name: string;
}
