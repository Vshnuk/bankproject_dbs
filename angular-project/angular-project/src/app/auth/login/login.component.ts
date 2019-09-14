import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { Customer } from 'src/app/services/blog.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  submitted = false;
  returnUrl: string;
  error: {errorTitle: '', errorDesc: ''};
  loginError: string;
  cus:Customer=new Customer(0,"","");

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService
    ) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
    this.returnUrl = '/homepage';
    this.authService.logout();
  }

  get username() { return this.loginForm.get('username'); }
  get password() { return this.loginForm.get('password'); }

  onSubmit() {

    this.submitted = true;
    this.cus.username=this.username.value;
    this.cus.password=this.password.value;
    this.cus.accno=0;
    this.authService.loginUser(this.cus).subscribe((data) => { 
        this.cus=data;
        this.validatecustomer();

     },error => this.error = error);
  }



  validatecustomer():void
  {
    if(this.cus.username!="")
    {
      this.authService.customerusername=this.cus.username;
      this.router.navigate(['/homepage/login/customer']);
      localStorage.setItem('isLoggedIn', "true");  
      localStorage.setItem('token', this.cus.username); 
    }
    else
    {
      this.loginError = 'Username or password is incorrect.';
    }
  }
}
