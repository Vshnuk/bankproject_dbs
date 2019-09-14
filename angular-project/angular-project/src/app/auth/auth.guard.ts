import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, CanActivateChild } from '@angular/router';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanActivateChild {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {      
      if (this.isLoggedIn()) {  return true; }      
      // navigate to login page as user is not authenticated      
      this.router.navigate(['/homepage/login']);      
      return false;      }

  canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    return this.canActivate(route, state);
  }

  checkLogin(url: string) {
    if (this.authService.isLoggedIn()) {
      return true;
    }

    this.authService.redirectUrl = url;

    this.router.navigate(['/login'], {queryParams: { returnUrl: url }} );
  }

  public isLoggedIn(): boolean {      
    let status = false;      
    if (localStorage.getItem('isLoggedIn') == "true") {      
       status = true;      
    }    
    else {      
       status = false;      
       }      
    return status;      
    }    
 }   

