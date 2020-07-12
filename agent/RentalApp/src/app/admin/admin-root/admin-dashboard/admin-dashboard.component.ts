import { Component, OnInit } from '@angular/core';
import { DashboardService } from '@app/dashboard/dashboard.service';
import { AlertService } from '@app/shared/alert/alert.service';
import { saveAs } from 'file-saver';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  loading = false;
  workingOnRatingReport = false;
  workingOnMileageReport = false;
  workingOnReviewsReport = false;

  constructor(
    private dashboardService: DashboardService,
    private alertService: AlertService) { }

  ngOnInit(): void {
  }

  getMileageReport() {
    this.loading = true;
    this.workingOnMileageReport = true;
    this.dashboardService.getMileageReport()
      .pipe(first())
      .subscribe(blob => {
        saveAs(blob, 'CarMileageReport.pdf');
        this.alertService.success('Mileage report created!', {
          keepAfterRouteChange: true, autoClose: true
        });
        this.loading = false;
        this.workingOnMileageReport = false;
      }, error => {
        this.alertService.error(error);
        this.loading = false;
        this.workingOnMileageReport = false;
      })
  }

  getRatingReport() {
    this.loading = true;
    this.workingOnRatingReport = true;
    this.dashboardService.getRatingReport()
      .pipe(first())
      .subscribe(blob => {
        saveAs(blob, 'CarRatingReport.pdf');
        this.alertService.success('Rating report created!', {
          keepAfterRouteChange: true, autoClose: true
        });
        this.loading = false;
        this.workingOnRatingReport = false;
      }, error => {
        this.alertService.error(error);
        this.loading = false;
        this.workingOnRatingReport = false;
      })
  }

}
