import {Component, inject, NgZone, OnInit, PLATFORM_ID} from '@angular/core';
import {HttpService} from "../../service/http.service";
import {Book} from "../../models/book";
import {AuthenticationService} from "../../service/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-list-book',
  templateUrl: './list-book.component.html',
  styleUrl: './list-book.component.css'
})
export class ListBookComponent implements OnInit {

  // private activatedRoute: ActivatedRoute // for retrieve params on path that sent by get method
  // private username: string | null
  protected headersBook: string[] = ['Order', 'Bid', 'Title', 'Price']
  private httpService: HttpService
  // private authenticationService: AuthenticationService
  private ngZone: NgZone
  private router: Router
  private declare jwt: string | null
  private _books: Book[] | undefined

  private pathBookEdit : string = "/book/edit"

  constructor(httpService: HttpService, router: Router, ngZone: NgZone) {
    this.httpService = httpService;
    this.router = router
    this.ngZone = ngZone
  }

  ngOnInit(): void {
    if (typeof window !== 'undefined' && window.sessionStorage) {
      this.jwt = sessionStorage.getItem('token')! // retrieve token from user can be logged in
      // let user = sessionStorage.getItem('username')!
      // console.log('user ' + user + ' has token ' + this.jwt)
    }
    this.prepareBooks();
  }


  private prepareBooks() {
    this.httpService.getProgramingBooks(this.jwt).subscribe((response) => {
        this._books = response
      },
      (error) => this.doLoginFailed(error) // case invalid role , user still log in
    ) // end subscribe()
  }

  private doLoginFailed(error: any) {
    if (error.status === 401) {
      if (typeof window !== 'undefined' && window.sessionStorage) {
        window.alert('login failed')
        this.ngZone.run(() => {
          this.router.navigateByUrl('') // go to path
        })
      }
    }
  }

  protected get books(): Book[] | undefined {
    return this._books;
  }

  protected getRouterLinkBookEdit(bid: string){
    return this.pathBookEdit+`/${bid}`
  }
}
