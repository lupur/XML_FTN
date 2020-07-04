
export interface ReviewVm {
	reviews: Review[];
}

export interface Review {
	id?: number;
	authorId: number;
	carId: number;
	authorDisplayName: string;
	authorEmail: string;
	rating: number;
	comment: string;
	status?: ReviewStatus;
	createdOn?: Date;
}

export enum ReviewStatus {
	PENDING = 'Pending',
	ACCEPTED = 'Accepted',
	REJECTED = 'Rejected'
}