import { Component, OnInit } from '@angular/core';
import { Rental } from '@app/models/rental';
import { RentalsService } from '../rentals.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-rentals-list',
  templateUrl: './rentals-list.component.html',
  styleUrls: ['./rentals-list.component.css']
})
export class RentalsListComponent implements OnInit {
  rentalRequests: Rental[];

  constructor(
    private rentalsService: RentalsService
  ) { }

  ngOnInit(): void {
    this.rentalsService.getAll()
      .pipe(first())
      .subscribe(rentalVm => this.rentalRequests = rentalVm.rentals);
  }

}
