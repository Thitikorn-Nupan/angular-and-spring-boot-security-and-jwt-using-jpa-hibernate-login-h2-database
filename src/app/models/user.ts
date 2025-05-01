class User {
  private _uid: string
  private _username: string
  private _password: string

  constructor(uid: string, username: string, password: string) {
    this._uid = uid;
    this._username = username;
    this._password = password;
  }

  get uid(): string {
    return this._uid;
  }

  set uid(value: string) {
    this._uid = value;
  }

  get username(): string {
    return this._username;
  }

  set username(value: string) {
    this._username = value;
  }

  get password(): string {
    return this._password;
  }

  set password(value: string) {
    this._password = value;
  }
}

export {
  User
}
