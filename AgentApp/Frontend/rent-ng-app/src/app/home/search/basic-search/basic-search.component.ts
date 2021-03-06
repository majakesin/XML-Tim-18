import { Component, OnInit } from '@angular/core';
import {Search} from '../../../model/search';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {SearchServiceService} from '../../../service/search-service.service';

@Component({
  selector: 'app-basic-search',
  templateUrl: './basic-search.component.html',
  styles: [
  ]
})
export class BasicSearchComponent implements OnInit {

  oglasi: Search [];

  form = new FormGroup({
    city: new FormControl(),
    // tslint:disable-next-line:max-line-length
    startOfAdvert: new FormControl(this.getTwoDaysForwardDate(), Validators.compose ([Validators.maxLength(10), this.dateValidationStart]) ),
    // tslint:disable-next-line:max-line-length
    endOfAdvert: new FormControl(this.getThreeDaysForwardDate(), Validators.compose ([Validators.maxLength(10), this.dateValidationEnd]) ), // god-mes-dan
  });

  constructor(private router: Router, private searchService: SearchServiceService ) { }

  ngOnInit(): void {}

  onSubmit(pretrazi) {
    const nextDay = this.getTwoDaysForwardDate();
    console.log(pretrazi);
    if (pretrazi.startOfAdvert < pretrazi.endOfAdvert) {
      if (pretrazi.startOfAdvert < nextDay ){
        alert('Start date must be 48h in future!');
        return;
      }

      this.searchService.searchOglase(pretrazi.city, pretrazi.startOfAdvert, pretrazi.endOfAdvert)
        .subscribe(
          (response: Search[]) => {
            this.oglasi = response;
            console.log(this.oglasi);
          }
        );
    } else {
      alert('Start date must be before end date !');
    }
  }

  dateValidationStart(control) {
    const today = new Date();
    const startD = new Date(control.value);
    if ((+control.value.slice(0, -6)) < (+today.getFullYear())) {// ako je  godina uneta < manja od danasnje - ne moze
      console.log('godina nije dobra');
      return {startOfAdvert: false};
    }
    if ((+control.value.slice(0, -6)) === (+today.getFullYear())) {

      if (+control.value.slice(5, -3) < (+(today.getMonth() + 1))) {// da li je u istoj godini mesec uneti manji od danasnjeg - ne moze
        console.log('mesec nije dobar');
        return {startOfAdvert: false};
      }
      if (+control.value.slice(5, -3) === (+(today.getMonth() + 1))) {// ako je mesec isti proveri dan
        if (+control.value.slice(8, 10) < (+(today.getDate()))) {
          console.log('dan nije dobar');
          return {startOfAdvert: false};
        }
      }
    }
  }
  dateValidationEnd(control) {
    const today = new Date();
    today.setDate(today.getDate() + 1);
    if ((+control.value.slice(0, -6)) < (+today.getFullYear())) {// ako je  godina uneta < manja od danasnje - ne moze
      console.log('godina nije dobra');
      return {endOfAdvert: false};
    }
    if ((+control.value.slice(0, -6)) === (+today.getFullYear())) {

      if (+control.value.slice(5, -3) < (+(today.getMonth() + 1))) {// da li je u istoj godini mesec uneti manji od danasnjeg - ne moze
        console.log('mesec nije dobar');
        return {endOfAdvert: false};
      }
      if (+control.value.slice(5, -3) === (+(today.getMonth() + 1))) {// ako je mesec isti proveri dan
        if (+control.value.slice(8, 10) < (+(today.getDate()))) {
          console.log('dan nije dobar');
          return {endOfAdvert: false};
        }
      }
    }
  }

  // zakazuje najmanje 48h
  getTwoDaysForwardDate(): string {
    const today = new Date();
    today.setDate(today.getDate() + 2);

    const dd = today.getDate();
    const mm = today.getMonth() + 1; // January is 0!
    const yyyy = today.getFullYear();
    let pd = dd.toString();
    let pm = mm.toString();
    if ( dd < 10 ) {
      pd = '0' + dd;
    }
    if (mm < 10) {
      pm = '0' + mm ;
    }
    const todayStr = yyyy + '-' + pm + '-' + pd ;
    return todayStr;
  }

  // kraj mora biti 1 dan kasnije od akazivanja
  getThreeDaysForwardDate(): string {

    const today = new Date();
    today.setDate(today.getDate() + 3);

    const dd = today.getDate();
    const mm = today.getMonth() + 1; // January is 0!
    const yyyy = today.getFullYear();
    let pd = dd.toString();
    let pm = mm.toString();
    if ( dd < 10 ) {
      pd = '0' + dd;
    }
    if (mm < 10) {
      pm = '0' + mm ;
    }
    const todayStr = yyyy + '-' + pm + '-' + pd ;
    return todayStr;
  }

  sortirajCeneRastuce(){
    this.oglasi.sort((a, b) => a.price - b.price);
  }

  sortirajCeneOpadajuce(){
    this.oglasi.sort((a, b) => b.price - a.price);
  }
}
