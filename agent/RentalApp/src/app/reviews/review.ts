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