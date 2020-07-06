import { Component, OnInit } from '@angular/core';
import { CarModel } from '@app/models/car-model';

@Component({
  selector: 'app-car-models-list',
  templateUrl: './car-models-list.component.html',
  styleUrls: ['./car-models-list.component.css']
})
export class CarModelsListComponent implements OnInit {
  carModels: CarModel[];

  constructor() { }

  ngOnInit(): void {
  }

}
