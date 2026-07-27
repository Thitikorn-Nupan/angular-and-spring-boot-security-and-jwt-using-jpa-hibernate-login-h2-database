import {Component, NgZone, OnInit} from '@angular/core';
import {AuthenticationService} from "../../service/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css'
})
export class LogoutComponent implements OnInit {

  constructor(private readonly authenticationService: AuthenticationService, private readonly router: Router, private readonly ngZone: NgZone) {
  }

  ngOnInit(): void {
    this.authenticationService.logOut()
    this.ngZone.run(() => {
      this.router.navigateByUrl('login').then(() => window.location.reload()) // go to path
    })
  }

}
