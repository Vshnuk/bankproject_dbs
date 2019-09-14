import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomepageComponent } from '../homepage/homepage.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  {path: 'homepage/login', component: LoginComponent },
  {path: 'homepage', component: HomepageComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
