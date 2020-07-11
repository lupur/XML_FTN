import { Component, OnInit } from '@angular/core';
import { Review, ReviewStatus } from '@app/reviews/review';
import { ReviewService } from '@app/reviews/review.service';
import { AlertService } from '@app/shared/alert/alert.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-review-list',
  templateUrl: './review-list.component.html',
  styleUrls: ['./review-list.component.css']
})
export class ReviewListComponent implements OnInit {
  reviews = null;

  loading = false;

  constructor(
    private reviewService: ReviewService,
    private alertService: AlertService) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.reviewService.getAll()
      .pipe(first())
      .subscribe(reviewVm => this.reviews = reviewVm.reviews);
  }

  accept(id: number) {
    this.loading = true;
    this.reviewService.update(id, ReviewStatus.ACCEPTED)
      .pipe(first())
      .subscribe(_ => {
        this.getAll();
        this.alertService.success('Review request accepted!', {
          keepAfterRouteChange: true, autoClose: true
        });
        this.loading = false;
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }

  reject(id: number) {
    this.loading = true;
    this.reviewService.update(id, ReviewStatus.REJECTED)
      .pipe(first())
      .subscribe(_ => {
        this.getAll();
        this.alertService.success('Review request rejected!', {
          keepAfterRouteChange: true, autoClose: true
        });
        this.loading = false;
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }

  switchStatus(status: string) {
    switch (status) {
      case ReviewStatus.ACCEPTED:
        return 'badge-success';
      case ReviewStatus.PENDING:
        return 'badge-warning';
      case ReviewStatus.REJECTED:
        return 'badge-danger';
    }
  }

}
