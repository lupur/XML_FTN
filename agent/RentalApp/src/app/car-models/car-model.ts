export interface CarModelVm {
	carModels: CarModel[];
}

export interface CarModel {
	name: string;
	carBrandName: string;
	isDeleting: boolean;
}
