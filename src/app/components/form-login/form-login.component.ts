import {Component, NgZone} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../service/authentication.service";
import {User} from "../../models/user";

@Component({
  selector: 'app-form-login',
  templateUrl: './form-login.component.html',
  styleUrl: './form-login.component.css'
})
export class FormLoginComponent {

  protected user: User

  constructor(private readonly router: Router, private readonly authenticationService: AuthenticationService, private readonly ngZone: NgZone) {
    this.user = new User('', '', '')
  }

  protected checkLogin() : void {
    (
      this.authenticationService.authenticate(this.user.username, this.user.password).subscribe((response) => { // (response)
          /* response {jwt: 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGV4IiwiZXhwIjoxN…iQcqsvrmMIR8xFy4Ol9GEGVMmY6MOL8m9xDlQguWav0nUmaVw'} */
          if (response.jwt) {
            this.ngZone.run(() => this.router.navigateByUrl('book/list').then(() => window.location.reload()))
          }
        },
        (error) => { // case login failed
          this.router.navigateByUrl('login').then(() => {
            window.location.reload()
          })
        })
    );
  }

}
