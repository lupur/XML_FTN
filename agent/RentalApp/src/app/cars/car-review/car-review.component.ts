import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '@app/auth/auth.service';
import { User } from '@app/users/user';
import { Review, ReviewStatus } from '@app/reviews/review';
import { ReviewService } from '@app/reviews/review.service';
import { AlertService } from '@app/shared/alert/alert.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-car-review',
  templateUrl: './car-review.component.html',
  styleUrls: ['./car-review.component.css']
})
export class CarReviewComponent implements OnInit {
  @Input() carId: number;

  form: FormGroup;
  loading = false;
  submitted = false;
  currentUser: User;
  reviews: Review[];

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private reviewService: ReviewService,
    private alertService: AlertService
  ) {
    this.currentUser = this.authService.userValue;
  }

  ngOnInit(): void {
    this.createForm();

    this.reviewService.getReviewsForCar(this.carId, ReviewStatus.ACCEPTED)
      .pipe(first())
      .subscribe(reviewVm => this.reviews = reviewVm.reviews);
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
      carId: this.carId,
      comment: this.form.value.comment,
      rating: this.form.value.rating
    }

    this.loading = true;
    this.reviewService.create(review)
      .pipe(first())
      .subscribe(_ => {
        this.alertService.success('Review sent successfully!');
        this.form.reset();
        this.submitted = false;
        this.loading = false;
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      })
  }

  private createForm() {
    this.form = this.formBuilder.group({
      comment: ['', [Validators.required]],
      rating: ['', [Validators.required, Validators.min(1), Validators.max(5)]]
    });
  }
}
