import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { Rental, RentalBundle, RentalStatus } from '../rental';
import { RentalService } from '../rental.service';
import { AlertService } from '@app/shared/alert/alert.service';

@Component({
  selector: 'app-rental-detail',
  templateUrl: './rental-detail.component.html',
  styleUrls: ['./rental-detail.component.css']
})
export class RentalDetailComponent implements OnInit {
  rentals: Rental[];
  currentBundle: RentalBundle;
  currentBundleId: number;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private rentalService: RentalService
  ) { }

  ngOnInit(): void {
    this.currentBundleId = this.route.snapshot.params['id'];

    this.getBundle(this.currentBundleId);
    if (!this.isAccepted() && !this.isRejected()) {
      this.getRentalsForBundle(this.currentBundleId);
    }
  }

  getBundle(id: number) {
    this.rentalService.getBundleById(id)
      .pipe(first())
      .subscribe(bundle => this.currentBundle = bundle);
  }

  getRentalsForBundle(id: number) {
    this.rentalService.getRentalsForBundleId(id)
      .pipe(first())
      .subscribe(rentalVm => this.rentals = rentalVm.rentals);
  }

  accept() {
    console.log(`Accepting rental bundle ${this.currentBundleId}`);
  }

  reject() {
    console.log(`Rejecting rental bundle ${this.currentBundleId}`);
  }

  isAccepted() {
    return this.currentBundle && this.currentBundle.status === RentalStatus.ACCEPTED;
  }

  isRejected() {
    return this.currentBundle && this.currentBundle.status === RentalStatus.REJECTED;
  }

  isPending() {
    return this.currentBundle && this.currentBundle.status === RentalStatus.PENDING;
  }

  gotoRentalBundles() {
    this.location.back();
  }
}
