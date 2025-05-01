import {Component, NgZone, OnInit} from '@angular/core';
import {AuthenticationService} from "../../service/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css'
})
export class LogoutComponent implements OnInit {

  private authenticationService: AuthenticationService
  private router: Router
  private ngZone: NgZone

  constructor(authenticationService: AuthenticationService, router: Router, ngZone: NgZone) {
    this.authenticationService = authenticationService;
    this.router = router;
    this.ngZone = ngZone;
  }

  ngOnInit(): void {
    this.authenticationService.logOut()
    /*let user = this.authenticationService.isUserLoggedInAndGetUser() // it will be null
    let jwt = this.authenticationService.isUserLoggedInAndGetToken() // it will be null*/
    /*console.log('(after deleted session storage) user '+user+' has token '+jwt)*/
    this.ngZone.run(() => {
      this.router.navigateByUrl('login').then(()=> {
        window.location.reload()
      }) // go to path
    })
  }

}
