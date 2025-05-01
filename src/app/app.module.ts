import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListBookComponent } from './components/list-book/list-book.component';
import { FormLoginComponent } from "./components/form-login/form-login.component";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { EditBookComponent } from './components/edit-book/edit-book.component';
import { LogoutComponent } from './components/logout/logout.component';
import { LoadingComponent } from './components/loading/loading.component';
import { MenuComponent } from './components/menu/menu.component';

@NgModule({
  declarations: [
    AppComponent,
    ListBookComponent,
    FormLoginComponent,
    EditBookComponent,
    LogoutComponent,
    LoadingComponent,
    MenuComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
