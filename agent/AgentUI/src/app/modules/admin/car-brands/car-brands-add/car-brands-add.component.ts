import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from '@app/shared/components/alert/alert.service';
import { first } from 'rxjs/operators';
import { CarBrandsService } from '../car-brands.service';

@Component({
  selector: 'app-car-brands-add',
  templateUrl: './car-brands-add.component.html',
  styleUrls: ['./car-brands-add.component.css']
})
export class CarBrandsAddComponent implements OnInit {
  form: FormGroup;

  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private alertService: AlertService,
    private carBrandsService: CarBrandsService
  ) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: ['', Validators.required]
    });
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;
    this.alertService.clear();

    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    this.carBrandsService.create(this.form.value)
      .pipe(first())
      .subscribe(_ => {
        this.alertService.success('Car brand added successfully', { keepAfterRouteChange: true, autoClose: true });
        this.router.navigate(['.', { relativeTo: this.route }]);
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }

}
