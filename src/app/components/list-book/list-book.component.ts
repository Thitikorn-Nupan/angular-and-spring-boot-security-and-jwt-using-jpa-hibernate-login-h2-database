import {Component, NgZone, OnInit} from '@angular/core';
import {HttpService} from "../../service/http.service";
import {Book} from "../../models/book";
import {Router} from "@angular/router";

@Component({
  selector: 'app-list-book',
  templateUrl: './list-book.component.html',
  styleUrl: './list-book.component.css'
})
export class ListBookComponent implements OnInit {

  protected readonly headersBook: string[] = ['Order', 'Bid', 'Title', 'Price']
  private declare jwt: string | null
  private _books: Book[] | undefined
  private readonly pathBookEdit: string = "/book/edit"

  constructor(private readonly httpService: HttpService, private readonly router: Router, private readonly ngZone: NgZone) {
  }

  ngOnInit(): void {
    if (typeof window !== 'undefined' && window.sessionStorage) {
      this.jwt = sessionStorage.getItem('token')! // retrieve token from user can be logged in
    }
    this.prepareBooks();
  }


  private prepareBooks() : void {
    this.httpService.getProgramingBooks(this.jwt).subscribe((response) =>  this._books = response,
      (error) => this.doLoginFailed(error) // case invalid role , user still log in
    ) // end subscribe()
  }

  private doLoginFailed(error: any): void {
    if (error.status === 401) {
      if (typeof window !== 'undefined' && window.sessionStorage) {
        window.alert('login failed')
        this.ngZone.run(() => this.router.navigateByUrl(''))
      }
    }
  }

  protected get books(): Book[] | undefined {
    return this._books;
  }

  protected getRouterLinkBookEdit(bid: string) {
    return this.pathBookEdit + `/${bid}`
  }
}
