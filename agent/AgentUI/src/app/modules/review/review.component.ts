import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ReviewService } from './review.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from '@app/shared/components/alert/alert.service';
import { first } from 'rxjs/operators';
import { User } from '@app/models';
import { AuthService } from '../auth/auth.service';
import { Review } from '@app/models/car';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  form: FormGroup;
  loading = false;
  submitted = false;
  review: string;
  currentUser: User;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private reviewService: ReviewService,
    private alertService: AlertService,
    private authService: AuthService
  ) {
    this.currentUser = this.authService.userValue;
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      comment: ['', [Validators.required]],
      rating: ['', Validators.required]
    });
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;
    this.alertService.clear();

    if (this.form.invalid) {
      return;
    }

    var review: Review = {
      authorId: this.currentUser.id,
      authorDisplayName: this.currentUser.firstName,
      authorEmail: this.currentUser.email,
      carId: this.route.snapshot.params['id'],
      comment: this.form.value.comment,
      rating: this.form.value.rating
    }

    this.loading = true;
    this.reviewService.create(review)
      .pipe(first())
      .subscribe(_ => {
        this.redirectTo(`cars/details/${this.route.snapshot.params['id']}`);
        this.alertService.success('Comment added successfully', { keepAfterRouteChange: true, autoClose: true });
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }

  redirectTo(uri: string) {
    this.router.navigateByUrl('/', { skipLocationChange: true })
      .then(() => this.router.navigate([uri]));
  }
}
