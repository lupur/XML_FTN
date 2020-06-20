export interface Car {
	id: number;
	agentId: number;
	agentContactInfo: string;
	carCategoryId: number;
	brand: string;
	carModel: string;
	productionYear: number;
	fuelType: string;
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