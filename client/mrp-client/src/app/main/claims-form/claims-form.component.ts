import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ClaimsService } from '../service/claims/claims.service';

@Component({
  selector: 'app-claims-form',
  templateUrl: './claims-form.component.html',
  styleUrls: ['./claims-form.component.css']
})
export class ClaimsFormComponent implements OnInit {

  claimForm: FormGroup = new FormGroup({});

  errorMessage: string = '';
  successMessage: string = '';

  constructor(private claimsService: ClaimsService) { }

  ngOnInit(): void {
    this.claimForm = new FormGroup({
      memberId: new FormControl('', [Validators.required]),
      name: new FormControl('', [Validators.required]),
      dateOfBirth: new FormControl('', [Validators.required]),
      dateOfAdmission: new FormControl('', [Validators.required]),
      dateOfDischarge: new FormControl('', [Validators.required]),
      billAmount: new FormControl('', [Validators.required]),
      dependentId: new FormControl('')
    });
  }

  get memberId() { return this.claimForm.controls['memberId']};
  get name() { return this.claimForm.controls['name']};
  get dateOfBirth() { return this.claimForm.controls['dateOfBirth']};
  get dateOfAdmission() { return this.claimForm.controls['dateOfAdmission']};
  get dateOfDischarge() { return this.claimForm.controls['dateOfDischarge']};
  get billAmount() { return this.claimForm.controls['billAmount']};
  get dependentId() { return this.claimForm.controls['dependentId']};

  onSubmitClick(){
    if(this.claimForm.valid){
      this.claimsService.addClaims(this.claimForm.value);
      this.claimForm.reset();
      this.successMessage = 'Claims added successfully';
    }
  }

}
