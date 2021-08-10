import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { CountryAndStateService } from '../service/country-and-state/country-and-state.service';
import * as moment from 'moment';

@Component({
  selector: 'app-portal-registration',
  templateUrl: './portal-registration.component.html',
  styleUrls: ['./portal-registration.component.css']
})
export class PortalRegistrationComponent implements OnInit {

  signUpForm: FormGroup = new FormGroup({});
  successMessage: string = '';
  errorMessage: string = '';

  countries: any[] = [];
  states: any[] = [];
  cities: any[] = [];

  now = new Date();
  year = this.now.getFullYear();
  month = this.now.getMonth();
  day = this.now.getDay();
  minDate = moment({year: this.year - 100, month: this.month, day: this.day}).format('YYYY-MM-DD');

  maxDate = moment({year: this.year - 18, month: this.month, day: this.day}).format('YYYY-MM-DD');


  constructor(private authenticationService: AuthenticationService, 
      private router: Router,
      private countryAndStateService: CountryAndStateService) { }

  ngOnInit(): void {
    this.countries = this.countryAndStateService.countries;

    this.signUpForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      address: new FormControl('', [Validators.required]),
      country: new FormControl('', [Validators.required]),
      state: new FormControl('', [Validators.required]),
      city: new FormControl('', [Validators.required]),
      pincode: new FormControl('', [Validators.required]),
      dateOfBirth: new FormControl('', [Validators.required]),
      age: new FormControl('18', [Validators.required]),
      contactNumber: new FormControl('', [Validators.required]),
      panNumber: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]
      ),
      password: new FormControl('', [Validators.required]),
      dependentDetails: new FormArray([])
    });
  }

  get name() { return this.signUpForm.controls['name']};
  get address() { return this.signUpForm.controls['address']};
  get country() { return this.signUpForm.controls['country']};
  get state() { return this.signUpForm.controls['state']};
  get city() { return this.signUpForm.controls['city']};
  get pincode() { return this.signUpForm.controls['pincode']};
  get dateOfBirth() { return this.signUpForm.controls['dateOfBirth']};
  get age() { return this.signUpForm.controls['age']};
  get contactNumber() { return this.signUpForm.controls['contactNumber']};
  get panNumber() { return this.signUpForm.controls['panNumber']};
  get email() { return this.signUpForm.controls['email']};
  get password() { return this.signUpForm.controls['password']};

  ngbDateStructModel = { day: new Date().getUTCDay(), month: new Date().getUTCMonth(), year: new Date().getUTCFullYear()};

  model: NgbDateStruct = this.ngbDateStructModel;
  date: { year: number, month: number } = {year: new Date().getFullYear(), month: new Date().getMonth()-1};
  today: any = new Date(); 

  selectCountry() {
    this.states = this.countries.filter((country) => this.country.value === country.country_name)[0].states;
  }

  onSubmitClick(){
    if(this.signUpForm.valid){
      let result = this.authenticationService.registerMember(this.signUpForm.value);
      if(result){
        this.signUpForm.reset();
        this.successMessage = 'Member Registered Successfully!';
      } else {
        this.errorMessage = 'Member Already Exist.';
      }
    } else{
      this.errorMessage = 'Invalid Form Submission';
    }
  }

}
