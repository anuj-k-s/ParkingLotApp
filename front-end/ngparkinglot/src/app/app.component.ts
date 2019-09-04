import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  getUrl = 'http://localhost:8080/api/api/parkings';
  parkings: any;
  errorAlert: boolean;
  successAlert: boolean;
  error_message: string;
  success_message: string;
  constructor(private http: HttpClient) {
    this.parkings = [];
    this.getAllParking();
  }
  vehicleForm: FormGroup;
  ngOnInit() {
    this.vehicleForm = new FormGroup({
      vehicleLot: new FormControl(),
      vehicleNumber: new FormControl(),
      vehicleDuration: new FormControl(),
      vehicleAmount: new FormControl()

    });

  }
  getAllParking() {
    debugger;
    this.http.get(this.getUrl).subscribe(
      data => {
        this.parkings = data;
      },
      error => {
        debugger;
        this.errorAlert = true;
        this.successAlert = false;
        this.error_message = error.error.message ? error.error.message : error.statusText;
      });
  }

  parkNewVehicle() {
    this.http.post(this.getUrl, this.vehicleForm.value).subscribe(
      data => {
        this.errorAlert = false;
        this.successAlert = true;
        this.parkings.push(data);
        this.success_message = 'Car is parked successfully';
      },
      error => {
        this.errorAlert = true;
        this.successAlert = false;
        console.log(error);
        this.error_message = error.error.message;
      });
  }
  calculateAmount(event) {
    let amount: number;

    const time: number = event.target.value;
    if (time > 60) {
      amount = ((time - 60) * 0.33) + 20;

    } else {
      amount = 20;
    }
    this.vehicleForm.patchValue({
      vehicleAmount: amount
    });
  }


}
