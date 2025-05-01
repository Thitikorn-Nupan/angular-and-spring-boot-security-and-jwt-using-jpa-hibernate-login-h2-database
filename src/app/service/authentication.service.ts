import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private httpClient :HttpClient
  // private readonly domains = environment.domains
  // dynamic env.ts (follow command npm)
  private readonly domain = environment.domain

  constructor(httpClient :HttpClient) {
    this.httpClient = httpClient
  }

  authenticate(username : string | undefined , password : string | undefined ) {
    // on linux localhost have to change to ip a
    return this.httpClient.post<any>(`http://${this.domain}:8082/jwt/token`,{username,password}).pipe(
        map(response => {
            // console.log(response) // {jwt: 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGV4IiwiZXhwIjoxN…7QV1RNN3ENWa7pqwxFQXTTFPBRXRpn5UbWvy9NVf33FpC41_A'}
            sessionStorage.setItem('username',''+username );
            // let tokenStr= 'Bearer '+response.jwt;
            let tokenStr = response.jwt;
            sessionStorage.setItem('token', tokenStr);
            return response;
          })
    );
  }


  /**
  isUserLoggedInAndGetToken() {
    return sessionStorage.getItem('token')
  }

  isUserLoggedInAndGetUser() {
    return sessionStorage.getItem('username')
  }
   */

  logOut() {
    // deleted all session that run-time As username , token
    sessionStorage.removeItem('token')
    sessionStorage.removeItem('username')
  }
}
