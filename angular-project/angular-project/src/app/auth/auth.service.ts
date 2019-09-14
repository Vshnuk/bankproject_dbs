import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Customer, CustomerProfiles, Bankaccount } from '../services/blog.service';

export class Transactions{
  constructor(
    public tno:number,
    public fromAccountId:number,
    public toAccountId:number,
    public amount:number,
    public type:string,
    public transaction_id:number,
    public timestamp:string
  ){}
}

export class FromToDate{
  constructor(
    public fromdate:Date,
    public todate:Date,
    public accno:number
  ){}
}


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  serverUrl = environment.baseUrl;
  errorData: {};
  adminusername:string;
  customerusername:string;

  constructor(private http: HttpClient) { }

  redirectUrl: string;

  loginUser(customer:Customer) {
    return this.http.post<Customer>("http://localhost:8080/customers/loginucheck",customer );
  }

  loginAdmin(customer:Customer) {
    return this.http.post<Customer>("http://localhost:8080/customers/loginacheck",customer );
  }

  CreateUser(customer:Customer,Customerprof:CustomerProfiles) {
    Customerprof.createdon=customer.password;
    return this.http.post<Customer>("http://localhost:8080/customers/register",Customerprof);
  }


  isLoggedIn() {
    if (localStorage.getItem('currentUser')) {
      return true;
    }
    return false;
  }

  getprofiles()
  {
    return this.http.get<CustomerProfiles[]>("http://localhost:8080/customers/getprofiles");
  }

  getSearchedprofile(customerprof:CustomerProfiles)
  {
    return this.http.post<CustomerProfiles[]>("http://localhost:8080/customers/getaccount",customerprof);
  }

  Modifyprofile(customerprof:CustomerProfiles)
  {
    return this.http.post<CustomerProfiles>("http://localhost:8080/customers/modpro",customerprof);
  }

  DeleteProfile(customerprof:CustomerProfiles)
  {
    return this.http.post<CustomerProfiles>("http://localhost:8080/customers/delpro",customerprof);
  }

  getAllBankAccounts(bankaccount)
  {
    return this.http.post<CustomerProfiles[]>("http://localhost:8080/customers/allaccounts",bankaccount);
  }

  SendMoney(transactions:Transactions)
  {
    return this.http.post<Transactions>("http://localhost:8080/customers/sendMoney1",transactions);
  }

  getAllTransactions(fromtodate:FromToDate)
  {
    return this.http.post<Transactions[]>("http://localhost:8080/customers/display",fromtodate);
  }

  

  getAuthorizationToken() {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    return currentUser.token;
  }

  logout() :void {    
    localStorage.setItem('isLoggedIn','false');    
    localStorage.removeItem('token');    
    } 


  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(`Backend returned code ${error.status}, ` + `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    this.errorData = {
      errorTitle: 'Oops! Request for document failed',
      errorDesc: 'Something bad happened. Please try again later.'
    };
    return throwError(this.errorData);
  }

}
