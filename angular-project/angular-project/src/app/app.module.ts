import { BrowserModule, Title} from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { BlogpostModule } from './blogpost/blogpost.module';
import { CmspageModule } from './cmspage/cmspage.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { BannerComponent } from './banner/banner.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

import { AdminModule } from './admin/admin.module';
import { AuthModule } from './auth/auth.module';

import { httpInterceptorProviders } from './http-interceptors/index';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginadminComponent } from './loginadmin/loginadmin.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthRoutingModule } from './auth/auth-routing.module';
import { CustomerComponent } from './customer/customer.component';
import { RegisterComponent } from './register/register.component';
import { AdminpageComponent } from './adminpage/adminpage.component';
import { AuthGuard } from './auth/auth.guard';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    BannerComponent,
    PageNotFoundComponent,
    HomepageComponent,
    LoginadminComponent,
    CustomerComponent,
    RegisterComponent,
    AdminpageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BlogpostModule,
    CmspageModule,
    AdminModule,
    AuthModule,
    AppRoutingModule,
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
    AuthRoutingModule
  ],
  providers: [Title, httpInterceptorProviders,AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
