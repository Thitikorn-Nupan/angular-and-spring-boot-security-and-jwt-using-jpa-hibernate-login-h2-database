import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Book} from "../models/book";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  private readonly httpClient: HttpClient
  // private readonly domains: string[] = ['localhost', 'thitikorn-nupan.com'];
  //private readonly domains = environment.domains
  // dynamic env.ts (follow command npm)
  private readonly domain = environment.domain

  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient
  }

  public getProgramingBooks(jwt: string | null): Observable<Book[]> { // Role USER can access only.
    const headers = new HttpHeaders({Authorization: 'Bearer ' + jwt});
    return this.httpClient.get<Book[]>(`http://${this.domain}:8080/api/book-store/programing/reads`, {headers});
  }

  public getProgramingBook(jwt: string | null, bid: string | null): Observable<Book> { // Role USER can access only.
    const headers = new HttpHeaders({Authorization: 'Bearer ' + jwt});
    return this.httpClient.get<Book>(`http://${this.domain}:8080/api/book-store/programing/read/${bid}`, {headers});
  }

}
