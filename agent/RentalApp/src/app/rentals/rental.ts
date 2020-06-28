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

export enum RentalStatus {
	PENDING = 'Pending',
	ACCEPTED = 'Accepted',
	REJECTED = 'Rejected'
}