import { CarModel } from './car-model';

export interface CarBrand {
	name: string;
	carModels: CarModel[];
}

export interface CarBrandVm {
	carBrands: CarBrand[];
}