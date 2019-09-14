import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LoginComponent } from './auth/login/login.component';
import { LoginadminComponent } from './loginadmin/loginadmin.component';
import { CustomerComponent } from './customer/customer.component';
import { RegisterComponent } from './register/register.component';
import { AdminpageComponent } from './adminpage/adminpage.component';
import { AuthGuard } from './auth/auth.guard';

const routes: Routes = [
  {path: '', redirectTo: '/homepage', pathMatch: 'full'},
  {path: 'homepage/register', component: RegisterComponent},
  {path: 'homepage/login', component: LoginComponent},
  {path: 'homepage/login/customer', component: CustomerComponent,canActivate : [AuthGuard]},
  {path: 'homepage/loginadmin', component: LoginadminComponent},
  {path: 'homepage/loginadmin/adminpage', component: AdminpageComponent,canActivate : [AuthGuard]},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
