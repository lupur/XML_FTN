export interface RentalBundleVm {
	rentalBundles: RentalBundle[];
}

export interface RentalBundle {
	id: number;
	numberOfItems: number;
	status: RentalStatus;
	createdOn: Date;
	rentals: Rental[];
}

export interface RentalVm {
	rentals: Rental[];
}

export interface Rental {
	id: number;
	rentalBundleId: number;
	carId: number;
	carBrand: string;
	carModel: string;
	carYear: number;
	customerFullName: string;
	customerContactInfo: string;
	pickupDate: Date;
	returnDate: Date;
	status: RentalStatus;
}

export interface BundleRequest {
	rentals: RentalRequest[];
}

export interface RentalRequest {
	ownerId: number;
	customerId: number;
	carId: number;
	startDate?: Date;
	endDate?: Date;
	remarks?: string;
	isBundle: boolean;
}

export interface RentalResponse {
	bundleId: number;
	status: RentalStatus;
}

export enum RentalStatus {
	PENDING = 'Pending',
	ACCEPTED = 'Accepted',
	REJECTED = 'Rejected'
}