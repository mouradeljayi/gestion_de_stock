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
    NouvelCltFrsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
