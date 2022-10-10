import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageLoginComponent } from './pages/page-login/page-login.component';
import { PageRegisterComponent } from './pages/page-register/page-register.component';
import { PageDashboardComponent } from './pages/page-dashboard/page-dashboard.component';
import { PageStatistiquesComponent } from './pages/page-statistiques/page-statistiques.component';
import { MenuComponent } from './components/menu/menu.component';
import { HeaderComponent } from './components/header/header.component';
import { PageArticleComponent } from './pages/articles/page-article/page-article.component';
import { DetailArticleComponent } from './pages/articles/detail-article/detail-article.component';
import { PaginationComponent } from './components/pagination/pagination.component';
import { ButtonActionComponent } from './components/button-action/button-action.component';
import { NouvelArticleComponent } from './pages/articles/nouvel-article/nouvel-article.component';
import { PageMvtstkComponent } from './pages/mvtstk/page-mvtstk/page-mvtstk.component';
import { DetailMvtstkComponent } from './pages/mvtstk/detail-mvtstk/detail-mvtstk.component';
import { DetailStockArticleComponent } from './pages/mvtstk/detail-stock-article/detail-stock-article.component';
import { DetailCltFrsComponent } from './components/detail-clt-frs/detail-clt-frs.component';
import { PageClientComponent } from './pages/clients/page-client/page-client.component';
import { PageFournisseurComponent } from './pages/fournisseurs/page-fournisseur/page-fournisseur.component';
import { NouvelCltFrsComponent } from './components/nouvel-clt-frs/nouvel-clt-frs.component';
import { CmdCltFrsComponent } from './components/cmd-clt-frs/cmd-clt-frs.component';
import { DetailCmdComponent } from './components/detail-cmd/detail-cmd.component';
import { PageCmdCltFrsComponent } from './pages/page-cmd-clt-frs/page-cmd-clt-frs.component';
import { NouvelleCmdCltFrsComponent } from './components/nouvelle-cmd-clt-frs/nouvelle-cmd-clt-frs.component';
import { PageCategoryComponent } from './pages/categories/page-category/page-category.component';
import { NouvelleCategoryComponent } from './pages/categories/nouvelle-category/nouvelle-category.component';
import { PageUsersComponent } from './pages/users/page-users/page-users.component';
import { DetailUsersComponent } from './pages/users/detail-users/detail-users.component';
import { NouvelUserComponent } from './pages/users/nouvel-user/nouvel-user.component';
import { PageProfilComponent } from './pages/profil/page-profil/page-profil.component';
import { ChangePasswordComponent } from './pages/profil/change-password/change-password.component';
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {InterceptorService} from "./services/interceptor/interceptor.service";

@NgModule({
  declarations: [
    AppComponent,
    PageLoginComponent,
    PageRegisterComponent,
    PageDashboardComponent,
    PageStatistiquesComponent,
    MenuComponent,
    HeaderComponent,
    PageArticleComponent,
    DetailArticleComponent,
    PaginationComponent,
    ButtonActionComponent,
    NouvelArticleComponent,
    PageMvtstkComponent,
    DetailMvtstkComponent,
    DetailStockArticleComponent,
    DetailCltFrsComponent,
    PageClientComponent,
    PageFournisseurComponent,
    NouvelCltFrsComponent,
    CmdCltFrsComponent,
    DetailCmdComponent,
    PageCmdCltFrsComponent,
    NouvelleCmdCltFrsComponent,
    PageCategoryComponent,
    NouvelleCategoryComponent,
    PageUsersComponent,
    DetailUsersComponent,
    NouvelUserComponent,
    PageProfilComponent,
    ChangePasswordComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule
    ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: InterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
