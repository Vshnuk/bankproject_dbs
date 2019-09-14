import { Component, OnInit } from '@angular/core';
import { CustomerProfiles, Customer } from '../services/blog.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public newCustomerProfile:CustomerProfiles=new CustomerProfiles(0,"","","","",0,"",15000);
  public newCus:Customer=new Customer(0,"","");
  loginForm: FormGroup;
  submitted = false;
  returnUrl: string;
  error: {errorTitle: '', errorDesc: ''};
  loginError: string;

  constructor(
    private router: Router,
    private authService: AuthService){}

  ngOnInit() {
  }


  onSubmitregister() {
    this.authService.CreateUser(this.newCus,this.newCustomerProfile).subscribe((data) => { 
      this.newCus=data;
      this.validatecustomer();
     });
  }


  validatecustomer():void
  {
    if(this.newCus.username!="")
    {
      alert("Hola ....! Account Created Successfully ...!  Please Login");
      this.router.navigate(['/homepage']);
    }
    else
    {
      this.loginError = 'Servers are busy.Please try again later';
    }
  }


}
