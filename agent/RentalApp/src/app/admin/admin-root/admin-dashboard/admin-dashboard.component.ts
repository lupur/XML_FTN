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

  constructor(
    private dashboardService: DashboardService,
    private alertService: AlertService) { }

  ngOnInit(): void {
  }

  getMileageReport() {
    this.loading = true;
    this.dashboardService.getMileageReport()
      .pipe(first())
      .subscribe(blob => {
        saveAs(blob, 'CarMileageReport.pdf');
        this.alertService.success('Mileage report created!', {
          keepAfterRouteChange: true, autoClose: true
        });
        this.loading = false;
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      })
  }

}
