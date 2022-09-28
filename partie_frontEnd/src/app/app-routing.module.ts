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
    children: [
      {
        path: 'statistiques',
        component: PageStatistiquesComponent
      },
      {
        path: 'articles',
        component: PageArticleComponent
      },
      {
        path: 'nouvelarticle',
        component: NouvelArticleComponent
      },
      {
        path: 'mouvementstock',
        component: PageMvtstkComponent
      },
      {
        path: 'clients',
        component: PageClientComponent
      },
      {
        path: 'nouvelclient',
        component: NouvelCltFrsComponent,
        data: {
          origin: 'client'
        }
      },
      {
        path: 'commandeclient',
        component: PageCmdCltFrsComponent,
        data: {
          origin: 'clients'
        }
      },
      {
        path: 'nouvellecommandeclient',
        component: NouvelleCmdCltFrsComponent,
        data: {
          origin: 'client'
        }
      },
      {
        path: 'fournisseurs',
        component: PageFournisseurComponent
      },
      {
        path: 'nouvelfournisseur',
        component: NouvelCltFrsComponent,
        data: {
          origin: 'fournisseur'
        }
      },
      {
        path: 'commandefournisseur',
        component: PageCmdCltFrsComponent,
        data: {
          origin: 'fournisseurs'
        }
      },
      {
        path: 'nouvellecommandefournisseur',
        component: NouvelleCmdCltFrsComponent,
        data: {
          origin: 'fournisseur'
        }
      },
      {
        path: 'categories',
        component: PageCategoryComponent
      },
      {
        path: 'nouvellecategory',
        component: NouvelleCategoryComponent
      },
      {
        path: 'users',
        component: PageUsersComponent
      },
      {
        path: 'nouveluser',
        component: NouvelUserComponent
      },
      {
        path: 'profil',
        component: PageProfilComponent
      },
      {
        path: 'changerpassword',
        component: ChangePasswordComponent
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
