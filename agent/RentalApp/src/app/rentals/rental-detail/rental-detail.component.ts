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
  rentals: Rental[];
  selectedRentalBundle: Rental;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private rentalService: RentalService
  ) { }

  ngOnInit(): void {
    this.route.paramMap
      .pipe(switchMap(params =>
        this.rentalService.getRentalsForBundle(+params.get('id'))))
      .subscribe(rentalVm => {
        this.rentals = rentalVm.rentals;
      });
  }

  accept(id:number){
    console.log(`Accepting rental bundle ${id}`);
  }

  reject(id:number){
    console.log(`Rejecting rental bundle ${id}`);
  }

  gotoRentalBundles() {
    this.location.back();
  }
}
