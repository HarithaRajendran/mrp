import { Component, OnInit } from '@angular/core';
import { CountryAndStateService } from './main/service/country-and-state/country-and-state.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  constructor(private countryAndStateService: CountryAndStateService) {}
  ngOnInit(): void {
    // this.countryAndStateService.getToken();
  }
  title = 'mrp-client';
}
