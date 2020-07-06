import { Component, OnInit } from '@angular/core';
import { DemoCarBrand } from './demo-car-brand';
import { DemoService } from './demo.service';
import { first } from 'rxjs/operators';
import { AlertService } from '@app/shared/components/alert/alert.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-demo',
  templateUrl: './demo.component.html',
  styleUrls: ['./demo.component.css']
})
export class DemoComponent implements OnInit {
  loading: boolean = false;
  demoCarBrands: DemoCarBrand[];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private alertService: AlertService,
    private demoService: DemoService) { }

  ngOnInit(): void {
    this.demoService.getAll()
      .pipe(first())
      .subscribe(result => this.demoCarBrands = result);
  }

  createDemoCarBrand(carBrandName: string) {

    this.loading = true;
    this.demoService.create(carBrandName)
      .pipe(first())
      .subscribe(_ => {
        this.alertService.success('Car added successfully', { keepAfterRouteChange: true, autoClose: true });
        this.router.navigate(['.', { relativeTo: this.route }]);
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }

}
