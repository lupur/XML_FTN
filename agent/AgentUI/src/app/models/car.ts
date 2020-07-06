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
	images: CarImage[];
	reviews: Review[];
}

export interface CarImage {
	id: number;
	uri: string;
}

export interface Review {
	id?: number;
	authorId: number;
	carId: number;
	authorDisplayName: string;
	authorEmail: string;
	rating: number;
	comment: string;
	createdOn?: Date;
}

export interface CarVm {
	cars: Car[];
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