import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DemoService } from '../demo.service';
import { first } from 'rxjs/operators';
import { DemoCarBrand } from '../demo-car-brand';

@Component({
  selector: 'app-demo-details',
  templateUrl: './demo-details.component.html',
  styleUrls: ['./demo-details.component.css']
})
export class DemoDetailsComponent implements OnInit {
  id: number;
  demoCarBrand: DemoCarBrand;

  constructor(
    private route: ActivatedRoute,
    private demoService: DemoService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.demoService.getById(this.id)
      .pipe(first())
      .subscribe(result => this.demoCarBrand = result);
  }
  
}
