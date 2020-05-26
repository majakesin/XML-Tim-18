import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroup, NgForm } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { TestService } from '../services/test.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private auth: AuthService, private test: TestService) { }

  ngOnInit(): void {
  }

  onLogin(form: NgForm) {

    //username je na backendu
    let username = form.value.email;
    let password = form.value.password;


    this.auth.login(username, password);
  }

  onTest() {
    this.test.test().subscribe();
  }
}
