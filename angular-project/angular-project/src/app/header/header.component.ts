import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { AuthService } from '../auth/auth.service';
import { AuthGuard } from '../auth/auth.guard';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor( private titleService: Title, private authService: AuthService,private authgaurd:AuthGuard) { 
    
  }

  ngOnInit() {
    
  }

  get isLoggedIn() { 
    return this.authgaurd.isLoggedIn(); }

  setPageTitle(title: string) {
    this.titleService.setTitle(title);
  }

  logout()
  {
    this.authService.logout()
  }

}
