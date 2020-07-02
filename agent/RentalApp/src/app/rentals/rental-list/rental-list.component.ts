import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';
import { RentalBundle, RentalStatus } from '../rental';
import { RentalService } from '../rental.service';

@Component({
  selector: 'app-rental-list',
  templateUrl: './rental-list.component.html',
  styleUrls: ['./rental-list.component.css']
})
export class RentalListComponent implements OnInit {
  rentalBundles: RentalBundle[];

  constructor(private rentalService: RentalService) { }

  ngOnInit(): void {
    this.rentalService.getAllBundles()
      .pipe(first())
      .subscribe(rentalVm => {
        return this.rentalBundles = rentalVm.rentalBundles;
      });
  }

  switchStatus(status: string) {
    switch (status) {
      case RentalStatus.ACCEPTED:
        return 'badge-success';
      case RentalStatus.PENDING:
        return 'badge-warning';
      case RentalStatus.REJECTED:
        return 'badge-danger';
    }
  }

}
