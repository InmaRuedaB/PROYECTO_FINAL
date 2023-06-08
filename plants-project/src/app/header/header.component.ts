import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';
import { StorageService } from '../helpers/storage.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private router:Router, private storageService: StorageService) {}

  login() {
    this.router.navigate(['login'])
  }

  isLoged():boolean {
    return this.storageService.isLoggedIn();
  }

  logout() {
    this.storageService.clean();
  }

  getUserName():string {
    return window.sessionStorage.getItem("user_name") || '';
  }

  
}
