import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { CustomerProfiles,  Bankaccount } from '../services/blog.service';

@Component({
  selector: 'app-adminpage',
  templateUrl: './adminpage.component.html',
  styleUrls: ['./adminpage.component.css']
})
export class AdminpageComponent implements OnInit {
  adminusername:String;
  route:ActivatedRoute;
  cus:CustomerProfiles=new CustomerProfiles(0,"","","","",0,"",0);
  customerprofiles:CustomerProfiles[];
  bank:Bankaccount=new Bankaccount(0,0,"");
  constructor(private authService: AuthService) { 
    this.onlogin();
  }
  ModifiesCustomerProfile:CustomerProfiles=new CustomerProfiles(0,"","","","",0,"",0);
  DeletedCustomerProfile:CustomerProfiles=new CustomerProfiles(0,"","","","",0,"",0);

  ngOnInit() {
    
  }

  onlogin()
  {
    this.authService.getprofiles().subscribe((data) => { 
      this.customerprofiles=data;
     });
    this.adminusername= this.authService.adminusername;
  }

  searchclicked()
  {  
    this.authService.getSearchedprofile(this.cus).subscribe((data) => { 
      
      this.customerprofiles=data;

     });

  }

  myFunc(val:any)
  {
    //this.customerprofiles.find()
    let index;
    for (index = 0; index < this.customerprofiles.length; index++) { 
      if(this.customerprofiles[index].accno==val)
      {
        this.DeletedCustomerProfile.adhaarno=this.customerprofiles[index].adhaarno;
        this.DeletedCustomerProfile.accountname=this.customerprofiles[index].accountname;
        this.DeletedCustomerProfile.accno=val;
      }
  } 
    
    
  }

  modifyclicked()
  {
    this.authService.Modifyprofile(this.ModifiesCustomerProfile).subscribe((data) => { 
      if(data.accountname!=""){alert("Modified Succesfully");}
      else{alert("Please check the account number..");}
     });

  }

  DeleteAccount()
  {
    this.authService.DeleteProfile(this.DeletedCustomerProfile).subscribe((data) => { 
      if(data.accountname!=""){alert("Deleted Succesfully");}
      else{alert("Please check the account number..");}
     });

  }

}
