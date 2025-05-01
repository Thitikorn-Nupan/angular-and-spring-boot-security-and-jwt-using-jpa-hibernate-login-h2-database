class Book {

  private _bid: string
  private _title: string
  private _price: number

  constructor(bid: string, title: string, price: number) {
    this._bid = bid;
    this._title = title;
    this._price = price;
  }

  get bid(): string {
    return this._bid;
  }

  set bid(value: string) {
    this._bid = value;
  }

  get title(): string {
    return this._title;
  }

  set title(value: string) {
    this._title = value;
  }

  get price(): number {
    return this._price;
  }

  set price(value: number) {
    this._price = value;
  }

}

export {
  Book
}
