export interface CarVm {
	cars: Car[];
}

export interface Car {
	id: number;
	carCategory: string;
	carBrand: string;
	carModel: string;
	productionYear: number;
	fuelType: FuelType;
	transmissionType: TransmissionType;
	color: string;
	location: string;
	mileage: number;
	mileageConstraing: number;
	numberOfSeats: number;
	averageRating: number;
}

export enum FuelType {
	Gasoline,
	Diesel,
	Hybrid,
	Electric
}

export enum TransmissionType {
	Manual,
	Automatic,
	SemiAutomatic
}
