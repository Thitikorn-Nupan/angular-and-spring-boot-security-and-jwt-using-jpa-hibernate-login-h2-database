import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListBookComponent} from "./components/list-book/list-book.component";
import { FormLoginComponent } from "./components/form-login/form-login.component";
import {EditBookComponent} from "./components/edit-book/edit-book.component";
import {LogoutComponent} from "./components/logout/logout.component";
import {LoadingComponent} from "./components/loading/loading.component";

const routes: Routes = [
  { path:'book/list', component:ListBookComponent },
  { path:'book/edit/:bid', component:EditBookComponent },
  { path:'logout', component:LogoutComponent },
  { path:'login', component:FormLoginComponent },
  { path:'', component:LoadingComponent } // set default path '' when user access localhost:4200
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
