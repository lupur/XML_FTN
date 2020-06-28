import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Rental } from '../rental';
import { RentalService } from '../rental.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-rental-detail',
  templateUrl: './rental-detail.component.html',
  styleUrls: ['./rental-detail.component.css']
})
export class RentalDetailComponent implements OnInit {
  rentals = null;
  currentId: number;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private rentalService: RentalService
  ) { }

  ngOnInit(): void {
    this.route.paramMap
      .pipe(switchMap(params => {
        this.currentId = +params.get('id');
        return this.rentalService.getRentalsForBundle(this.currentId);
      }))
      .subscribe(rentalVm => {
        this.rentals = rentalVm.rentals;
      });
  }

  accept() {
    console.log(`Accepting rental bundle ${this.currentId}`);
  }

  reject() {
    console.log(`Rejecting rental bundle ${this.currentId}`);
  }

  gotoRentalBundles() {
    this.location.back();
  }
}
