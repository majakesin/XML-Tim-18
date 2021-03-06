import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/AuthService';

@Component({
  selector: 'app-nav-admin',
  templateUrl: './nav-admin.component.html',
  styleUrls: ['./nav-admin.component.css']
})
export class NavAdminComponent implements OnInit {

  constructor(private router: Router, private auth: AuthService) { }

  ngOnInit(): void {
  }

  onAdminHome() {
    this.router.navigate(['/admin']);
  }

  onAdminComment() {
    this.router.navigate(['/admin/comment']);
  }

  onAdminBrand() {
    this.router.navigate(['/admin/brand']);
  }

  onAdminModel() {
    this.router.navigate(['/admin/model']);
  }

  onAdminClass() {
    this.router.navigate(['/admin/class']);
  }

  onAdminTran() {
    this.router.navigate(['/admin/transmission']);
  }

  onAdminF() {
    this.router.navigate(['/admin/fuel-type']);
  }

  onRegister() {
    this.router.navigate(['/register/aprove']);
  }

  onLogout() {
    this.auth.logout();
  }

}
