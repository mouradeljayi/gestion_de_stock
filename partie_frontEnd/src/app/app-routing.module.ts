import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PageLoginComponent} from "./pages/page-login/page-login.component";
import {PageRegisterComponent} from "./pages/page-register/page-register.component";
import {PageDashboardComponent} from "./pages/page-dashboard/page-dashboard.component";
import {PageStatistiquesComponent} from "./pages/page-statistiques/page-statistiques.component";
import {PageArticleComponent} from "./pages/articles/page-article/page-article.component";
import {NouvelArticleComponent} from "./pages/articles/nouvel-article/nouvel-article.component";
import {PageMvtstkComponent} from "./pages/mvtstk/page-mvtstk/page-mvtstk.component";
import {PageClientComponent} from "./pages/clients/page-client/page-client.component";
import {PageFournisseurComponent} from "./pages/fournisseurs/page-fournisseur/page-fournisseur.component";
import {NouvelCltFrsComponent} from "./components/nouvel-clt-frs/nouvel-clt-frs.component";
import {PageCmdCltFrsComponent} from "./pages/page-cmd-clt-frs/page-cmd-clt-frs.component";
import {NouvelleCmdCltFrsComponent} from "./components/nouvelle-cmd-clt-frs/nouvelle-cmd-clt-frs.component";
import {PageCategoryComponent} from "./pages/categories/page-category/page-category.component";
import {NouvelleCategoryComponent} from "./pages/categories/nouvelle-category/nouvelle-category.component";
import {PageUsersComponent} from "./pages/users/page-users/page-users.component";
import {NouvelUserComponent} from "./pages/users/nouvel-user/nouvel-user.component";
import {PageProfilComponent} from "./pages/profil/page-profil/page-profil.component";
import {ChangePasswordComponent} from "./pages/profil/change-password/change-password.component";
import {ApplicationGuardService} from "./services/guard/application-guard.service";

const routes: Routes = [
  {
    path: 'login',
    component: PageLoginComponent
  },
  {
    path: 'inscrire',
    component: PageRegisterComponent
  },
  {
    path: '',
    component: PageDashboardComponent,
    canActivate: [ApplicationGuardService],
    children: [
      {
        path: 'statistiques',
        component: PageStatistiquesComponent,
        canActivate: [ApplicationGuardService]
      },
      {
        path: 'articles',
        component: PageArticleComponent,
        canActivate: [ApplicationGuardService]
      },
      {
        path: 'nouvelarticle',
        component: NouvelArticleComponent
      },
      {
        path: 'mouvementstock',
        component: PageMvtstkComponent,
        canActivate: [ApplicationGuardService]
      },
      {
        path: 'clients',
        component: PageClientComponent,
        canActivate: [ApplicationGuardService]
      },
      {
        path: 'nouvelclient',
        component: NouvelCltFrsComponent,
        canActivate: [ApplicationGuardService],
        data: {
          origin: 'client'
        }
      },
      {
        path: 'commandeclient',
        component: PageCmdCltFrsComponent,
        canActivate: [ApplicationGuardService],
        data: {
          origin: 'clients'
        }
      },
      {
        path: 'nouvellecommandeclient',
        component: NouvelleCmdCltFrsComponent,
        canActivate: [ApplicationGuardService],
        data: {
          origin: 'client'
        }
      },
      {
        path: 'fournisseurs',
        component: PageFournisseurComponent,
        canActivate: [ApplicationGuardService]
      },
      {
        path: 'nouvelfournisseur',
        component: NouvelCltFrsComponent,
        canActivate: [ApplicationGuardService],
        data: {
          origin: 'fournisseur'
        }
      },
      {
        path: 'commandefournisseur',
        component: PageCmdCltFrsComponent,
        canActivate: [ApplicationGuardService],
        data: {
          origin: 'fournisseurs'
        }
      },
      {
        path: 'nouvellecommandefournisseur',
        component: NouvelleCmdCltFrsComponent,
        canActivate: [ApplicationGuardService],
        data: {
          origin: 'fournisseur'
        }
      },
      {
        path: 'categories',
        component: PageCategoryComponent,
        canActivate: [ApplicationGuardService]
      },
      {
        path: 'nouvellecategory',
        component: NouvelleCategoryComponent,
        canActivate: [ApplicationGuardService]
      },
      {
        path: 'users',
        component: PageUsersComponent,
        canActivate: [ApplicationGuardService]
      },
      {
        path: 'nouveluser',
        component: NouvelUserComponent,
        canActivate: [ApplicationGuardService]
      },
      {
        path: 'profil',
        component: PageProfilComponent,
        canActivate: [ApplicationGuardService]
      },
      {
        path: 'changerpassword',
        component: ChangePasswordComponent,
        canActivate: [ApplicationGuardService]
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
