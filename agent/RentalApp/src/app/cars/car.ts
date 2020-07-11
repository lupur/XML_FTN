import { Review } from '@app/reviews/review';

export interface CarVm {
	cars: Car[];
}

export interface Car {
	id: number;
	ownerId: number;
	ownerFullName: string;
	ownerContactInfo: string;
	carCategory: string;
	carBrand: string;
	carModel: string;
	productionYear: number;
	fuelType: FuelType;
	transmissionType: TransmissionType;
	color: string;
	location: string;
	mileage: number;
	mileageConstraint: number;
	numberOfSeats: number;
	averageRating: number;
	reviews: Review[];
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
