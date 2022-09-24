import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PageLoginComponent } from './pages/page-login/page-login.component';
import {RouterOutlet} from "@angular/router";
import { PageRegisterComponent } from './pages/page-register/page-register.component';

@NgModule({
  declarations: [
    AppComponent,
    PageLoginComponent,
    PageRegisterComponent
  ],
    imports: [
        BrowserModule,
        RouterOutlet
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
