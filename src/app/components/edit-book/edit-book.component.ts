import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HttpService} from "../../service/http.service";
import {Book} from "../../models/book";

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrl: './edit-book.component.css'
})
export class EditBookComponent implements OnInit {

  private _book: Book | undefined
  private declare jwt: string | null
  protected selectDefault: boolean = false;

  constructor(private readonly httpService: HttpService, private readonly activatedRoute: ActivatedRoute) {
    this.httpService = httpService
    this.activatedRoute = activatedRoute
  }

  ngOnInit(): void {
    if (typeof window !== 'undefined' && window.sessionStorage) {
      this.jwt = sessionStorage.getItem('token')! // retrieve token from user can be logged in
      this.httpService.getProgramingBook(this.jwt, this.activatedRoute.snapshot.paramMap.get("bid")).subscribe((response: Book) => {
        this._book = new Book(response.bid, response.title, response.price)
        console.log(this._book.title)
        /**
         Can do
         this._book.bid = response.bid
         this._book.title = response.title
         this._book.price = response.price
         Can do
         this._book.setBid(response.bid)
         this._book.setTitle(response.title)
         this._book.setPrice(response.price)
         */
      })
    }
  }

  get book(): Book | undefined {
    return this._book;
  }

}
