import { Component, OnInit } from '@angular/core';
import { AuthService, Transactions, FromToDate } from '../auth/auth.service';
import { Bankaccount, CustomerProfiles} from '../services/blog.service';


@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  customerusername:String;
  allbankaccounts:CustomerProfiles[]=[];
  allTransactions:Transactions[]=[];
  myBankProfile:CustomerProfiles=new CustomerProfiles(0,"","","","",0,"",0);
  tran:Transactions=new Transactions(0,0,0,0,"",0,"");
  amountInSelectedAccount:number=0;
  fromtodate:FromToDate=new FromToDate(new Date(2018,19),new Date(),0);
  
  constructor(private authservice:AuthService) {
    this.onlogin();
   }


  ngOnInit() {
  }

  onlogin()
  {
    this.myBankProfile.accountname=this.authservice.customerusername;
    this.authservice.getAllBankAccounts(this.myBankProfile).subscribe((data) => { 
      this.allbankaccounts=data;
      this.tran.fromAccountId=this.allbankaccounts[0].accno;
      this.myBankProfile.email=this.allbankaccounts[0].email;
      this.myBankProfile.adhaarno=this.allbankaccounts[0].adhaarno;
      this.myBankProfile.address=this.allbankaccounts[0].address;
      this.amountInSelectedAccount=this.allbankaccounts[0].balance;
      this.fromtodate.accno=this.tran.fromAccountId;
      this.authservice.getAllTransactions(this.fromtodate).subscribe((data) => { 
      this.allTransactions=data;
    });
     });
  }

  sendMoney()
  {
    this.authservice.SendMoney(this.tran).subscribe((data) => { 
      if(data.fromAccountId!=-1 && data.toAccountId!=0)
      {alert("Transferred Succesfully");this.onlogin();}
      else if(data.fromAccountId==-1 && data.toAccountId==0){alert("Transaction Failed ..crossed daily limit of 10000");}
      else{alert("Error occurred.Please check the To account number");}
     });

  }

  myFunc(val:any)
  {
    this.tran.fromAccountId=val;
    let index;
    for (index = 0; index < this.allbankaccounts.length; index++) { 
      if(this.allbankaccounts[index].accno==val)
      {
        this.amountInSelectedAccount=this.allbankaccounts[index].balance;
      }
    }
    this.Datefilter();
  }

  Datefilter()
  {
    this.fromtodate.accno=this.tran.fromAccountId;
      this.authservice.getAllTransactions(this.fromtodate).subscribe((data) => { 
      this.allTransactions=data;
      })
  }
    


}
