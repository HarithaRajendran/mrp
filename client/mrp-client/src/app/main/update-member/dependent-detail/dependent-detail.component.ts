import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { AuthenticationService } from '../../service/authentication/authentication.service';

@Component({
  selector: 'app-dependent-detail',
  templateUrl: './dependent-detail.component.html',
  styleUrls: ['./dependent-detail.component.css']
})
export class DependentDetailComponent implements OnInit {

  dependentForm: FormGroup = new FormGroup({});
  dependent: FormArray = new FormArray([]);

  allDependentDetailId: any[] = [];
  greaterId: number = 0;

  constructor(private formBuilder: FormBuilder, private authenticationService: AuthenticationService) {}

  ngOnInit(): void {

    this.authenticationService.userDetails.forEach((user) => {
      user.dependentDetail?.forEach((dependent)=> {
        this.allDependentDetailId.push(dependent.dependentId as any as number);
      });
    });

    this.greaterId = this.allDependentDetailId.reduce((a, b) => Math.max(a, b));

    this.dependentForm = new FormGroup({
      dependent: new FormArray([])
    })
  }

  createDependent(): FormGroup {
    return this.formBuilder.group({
      memberId: this.greaterId+1,
      name: '',
      dob: ''
    })
  }

  addDependent(): void {
    this.dependent = this.dependentForm.get('dependent') as FormArray;
    this.dependent.push(this.createDependent());
  }
}
