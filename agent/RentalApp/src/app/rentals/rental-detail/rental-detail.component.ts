import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlertService } from '@app/shared/alert/alert.service';
import { first } from 'rxjs/operators';
import { Rental, RentalBundle, RentalResponse, RentalStatus } from '../rental';
import { RentalService } from '../rental.service';

@Component({
  selector: 'app-rental-detail',
  templateUrl: './rental-detail.component.html',
  styleUrls: ['./rental-detail.component.css']
})
export class RentalDetailComponent implements OnInit {
  rentals: Rental[];
  currentBundle: RentalBundle;
  currentBundleId: number;

  accepting = false;
  rejecting = false;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private rentalService: RentalService,
    private alertService: AlertService
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
    this.accepting = true;
    let response: RentalResponse = {
      bundleId: this.currentBundleId,
      status: RentalStatus.ACCEPTED
    }
    this.rentalService.updateRentalRequest(this.currentBundleId, response)
      .pipe(first())
      .subscribe(_ => {
        this.alertService.success('Rental request accepted!', {
          keepAfterRouteChange: true, autoClose: true
        });
        this.gotoRentalBundles();
      }, error => {
        this.alertService.error(error);
        this.accepting = false;
      });
  }

  reject() {
    this.rejecting = true;
    let response: RentalResponse = {
      bundleId: this.currentBundleId,
      status: RentalStatus.REJECTED
    }
    this.rentalService.updateRentalRequest(this.currentBundleId, response)
      .pipe(first())
      .subscribe(_ => {
        this.alertService.warn('Rental request rejected!', {
          keepAfterRouteChange: true, autoClose: true
        });
        this.gotoRentalBundles();
      }, error => {
        this.alertService.error(error);
        this.rejecting = false;
      });
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
