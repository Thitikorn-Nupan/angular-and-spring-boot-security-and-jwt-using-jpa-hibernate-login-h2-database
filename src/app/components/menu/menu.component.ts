import {Component, NgZone, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'menubar',
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent implements OnInit {
  protected isUserLoggedIn: boolean = false;
  protected readonly title: string;
  protected readonly routersLink: { router: string, label: string, header?: string } [];

  constructor(private readonly router: Router, private readonly ngZone: NgZone) {
    this.title = 'Angular + Spring Boot (Authenticate JWT By Spring Security)'
    this.routersLink = [
      {router: 'logout', label: 'Logout'},
      {router: 'login', label: 'Login'},
      {router: 'book/list', label: 'Book Table', header: 'Book store (API)'},
    ]
  }

  ngOnInit(): void {
    if (typeof window !== 'undefined' && window.sessionStorage) {
      this.isUserLoggedIn = sessionStorage.getItem('token') !== null;
    }
  }

  protected logoutClick() : void {
    sessionStorage.clear()
    this.ngZone.run(() => this.router.navigateByUrl('').then(() => window.location.reload()))
  }
}
