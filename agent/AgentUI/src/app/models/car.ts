export interface Car {
	id: number;
	agentId: number;
	agentContactInfo: string;
	carCategory: number;
	carBrand: string;
	carModel: string;
	productionYear: number;
	fuelType: FuelType;
	transmissionType: string;
	color: string;
	location: string;
	mileage: number;
	mileageConstraint?: number;
	numberOfSeats: number;
	averageRating: number;
	images:CarImage[];
}

export interface CarImage {
	id: number;
	uri: string;
}

export interface CarVm {
	cars: Car[];
}

export enum FuelType{
	Gasoline,
	Diesel,
	Hybrid,
	Electric
}