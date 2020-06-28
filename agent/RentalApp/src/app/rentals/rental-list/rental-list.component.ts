import { Component, OnInit } from '@angular/core';
import { RentalBundle } from '../rental';
import { RentalService } from '../rental.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-rental-list',
  templateUrl: './rental-list.component.html',
  styleUrls: ['./rental-list.component.css']
})
export class RentalListComponent implements OnInit {
  rentalBundles: RentalBundle[];

  constructor(private rentalService: RentalService) { }

  ngOnInit(): void {
    this.rentalService.getAll()
      .pipe(first())
      .subscribe(rentalVm => {
        return this.rentalBundles = rentalVm.rentalBundles;
      });
  }

}
