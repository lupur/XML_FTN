import { CarBrandService } from './../../car-brands/car-brand.service';
import { AlertService } from '@app/shared/alert/alert.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { CarBrand } from '@app/car-brands/car-brand';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-manage-brands',
  templateUrl: './manage-brands.component.html',
  styleUrls: ['./manage-brands.component.css']
})
export class ManageBrandsComponent implements OnInit {
  form: FormGroup;
  carBrands: CarBrand[];

  loading = false;
  submitted = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private alertService: AlertService,
    private carBrandService: CarBrandService
  ) { }

  get f() { return this.form.controls; }

  ngOnInit(): void {
    this.loading = true;
    this.carBrandService.getAll()
      .pipe(first())
      .subscribe(data => {
        this.carBrands = data;
        this.loading = false;
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      });

    this.form = this.formBuilder.group({
      name: ['', Validators.required]
    });
  }

  onSubmit() {
    this.submitted = true;
    this.alertService.clear();

    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    this.carBrandService.create(this.form.value)
      .pipe(first())
      .subscribe(data => {
        this.alertService.success('Car brand added successfully', {
          keepAfterRouteChange: true, autoClose: true, fade: true
        });
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }

}
