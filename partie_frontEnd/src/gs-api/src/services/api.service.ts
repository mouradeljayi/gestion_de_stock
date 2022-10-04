/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import {Observable, Observable as __Observable} from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { ArticleDto } from '../models/article-dto';
import { LigneCommandeClientDto } from '../models/ligne-commande-client-dto';
import { LigneCommandeFournisseurDto } from '../models/ligne-commande-fournisseur-dto';
import { LigneVenteDto } from '../models/ligne-vente-dto';
import { CategorieDto } from '../models/categorie-dto';
import { ClientDto } from '../models/client-dto';
import { CommandeClientDto } from '../models/commande-client-dto';
import { CommandeFournisseurDto } from '../models/commande-fournisseur-dto';
import { EntrepriseDto } from '../models/entreprise-dto';
import { FournisseurDto } from '../models/fournisseur-dto';
import { MouvementStockDto } from '../models/mouvement-stock-dto';
import { UtilisateurDto } from '../models/utilisateur-dto';
import { VenteDto } from '../models/vente-dto';
import { AuthenticationResponse } from '../models/authentication-response';
import { AuthenticationRequest } from '../models/authentication-request';
@Injectable({
  providedIn: 'root',
})
class ApiService extends __BaseService {
  static readonly findAllPath = '/gestiondestock/v1/articles/all';
  static readonly savePath = '/gestiondestock/v1/articles/create';
  static readonly deletePath = '/gestiondestock/v1/articles/delete/{idArticle}';
  static readonly findHistoriqueCommandeClientPath = '/gestiondestock/v1/articles/historique/commandesclient/{idArticle}';
  static readonly findHistoriqueCommandeFournisseurPath = '/gestiondestock/v1/articles/historique/commandesfournisseur/{idArticle}';
  static readonly findHistoriqueVentesPath = '/gestiondestock/v1/articles/historique/ventes/{idArticle}';
  static readonly findbyCodeArticlePath = '/gestiondestock/v1/articles/{codeArticle}';
  static readonly findbyIdPath = '/gestiondestock/v1/articles/{idArticle}';
  static readonly findAllArticlesByIdCategoryPath = '/gestiondestock/v1/articles/{idCategory}';
  static readonly findAll_1Path = '/gestiondestock/v1/categories/all';
  static readonly save_1Path = '/gestiondestock/v1/categories/create';
  static readonly delete_1Path = '/gestiondestock/v1/categories/delete/{idCategorie}';
  static readonly findbyCodePath = '/gestiondestock/v1/categories/{codeCategorie}';
  static readonly findbyId_1Path = '/gestiondestock/v1/categories/{idCategorie}';
  static readonly findAll_2Path = '/gestiondestock/v1/clients/all';
  static readonly save_2Path = '/gestiondestock/v1/clients/create';
  static readonly delete_2Path = '/gestiondestock/v1/clients/delete/{idClient}';
  static readonly findbyId_2Path = '/gestiondestock/v1/clients/{idClient}';
  static readonly findAll_3Path = '/gestiondestock/v1/commandesclients/all';
  static readonly deleteArticlePath = '/gestiondestock/v1/commandesclients/article/delete/{idCommande}/{idLigneCommande}';
  static readonly updateArticlePath = '/gestiondestock/v1/commandesclients/article/update/{idCommande}/{idLigneCommande}/{idArticle}';
  static readonly updateClientPath = '/gestiondestock/v1/commandesclients/client/update/{idCommande}/{idClient}';
  static readonly save_3Path = '/gestiondestock/v1/commandesclients/create';
  static readonly delete_3Path = '/gestiondestock/v1/commandesclients/delete/{idCommandeClient}';
  static readonly updateEtatCommandePath = '/gestiondestock/v1/commandesclients/etat/update/{idCommande}/{etatCommande}';
  static readonly findAllLignesCommandesClientByCommandeClientIdPath = '/gestiondestock/v1/commandesclients/lignescommande/{idCommande}';
  static readonly updateQuantiteCommandePath = '/gestiondestock/v1/commandesclients/quantite/update/{idCommande}/{idLigneCommande}/{quantite}';
  static readonly findByCodePath = '/gestiondestock/v1/commandesclients/{codeCommandeClient}';
  static readonly findbyId_3Path = '/gestiondestock/v1/commandesclients/{idCommandeClient}';
  static readonly findAll_4Path = '/gestiondestock/v1/commandesfournisseurs/all';
  static readonly deleteArticle_1Path = '/gestiondestock/v1/commandesfournisseurs/article/delete/{idCommande}/{idLigneCommande}';
  static readonly updateArticle_1Path = '/gestiondestock/v1/commandesfournisseurs/article/update/{idCommande}/{idLigneCommande}/{idArticle}';
  static readonly updateFournisseurPath = '/gestiondestock/v1/commandesfournisseurs/client/update/{idCommande}/{idFournisseur}';
  static readonly save_4Path = '/gestiondestock/v1/commandesfournisseurs/create';
  static readonly delete_4Path = '/gestiondestock/v1/commandesfournisseurs/delete/{idCommandeFournisseur}';
  static readonly updateEtatCommande_1Path = '/gestiondestock/v1/commandesfournisseurs/etat/update/{idCommande}/{etatCommande}';
  static readonly findAllLignesCommandesFournisseurByCommandeFournisseurIdPath = '/gestiondestock/v1/commandesfournisseurs/lignescommande/{idCommande}';
  static readonly updateQuantiteCommande_1Path = '/gestiondestock/v1/commandesfournisseurs/quantite/update/{idCommande}/{idLigneCommande}/{quantite}';
  static readonly findByCode_1Path = '/gestiondestock/v1/commandesfournisseurs/{codeCommandeFournisseur}';
  static readonly findbyId_4Path = '/gestiondestock/v1/commandesfournisseurs/{idCommandeFournisseur}';
  static readonly findAll_5Path = '/gestiondestock/v1/entreprises/all';
  static readonly save_5Path = '/gestiondestock/v1/entreprises/create';
  static readonly delete_5Path = '/gestiondestock/v1/entreprises/delete/{idEntreprise}';
  static readonly findbyId_5Path = '/gestiondestock/v1/entreprises/{idEntreprise}';
  static readonly findAll_6Path = '/gestiondestock/v1/fournisseurs/all';
  static readonly save_6Path = '/gestiondestock/v1/fournisseurs/create';
  static readonly delete_6Path = '/gestiondestock/v1/fournisseurs/delete/{idFournisseur}';
  static readonly findbyId_6Path = '/gestiondestock/v1/fournisseurs/{idFournisseur}';
  static readonly mvtStkArticlePath = '/gestiondestock/v1/mouvementstock/articles/{idArticle}';
  static readonly correctionStockNegPath = '/gestiondestock/v1/mouvementstock/correctionneg';
  static readonly correctionStockPosPath = '/gestiondestock/v1/mouvementstock/correctionpos';
  static readonly entreeStockPath = '/gestiondestock/v1/mouvementstock/entree';
  static readonly sortieStockPath = '/gestiondestock/v1/mouvementstock/sortie';
  static readonly stockReelArticlePath = '/gestiondestock/v1/mouvementstock/stockreel/{idArticle}';
  static readonly savePhotoPath = '/gestiondestock/v1/photos/{id}/{title}/{context}';
  static readonly findAll_7Path = '/gestiondestock/v1/utilisateurs/all';
  static readonly changerMotDePassePath = '/gestiondestock/v1/utilisateurs/changepassword';
  static readonly save_7Path = '/gestiondestock/v1/utilisateurs/create';
  static readonly delete_7Path = '/gestiondestock/v1/utilisateurs/delete/{idUtilisateur}';
  static readonly findByEmailPath = '/gestiondestock/v1/utilisateurs/{email}';
  static readonly findbyId_7Path = '/gestiondestock/v1/utilisateurs/{idUtilisateur}';
  static readonly findAll_8Path = '/gestiondestock/v1/ventes/all';
  static readonly save_8Path = '/gestiondestock/v1/ventes/create';
  static readonly delete_8Path = '/gestiondestock/v1/ventes/delete/{idVente}';
  static readonly findbyCode_1Path = '/gestiondestock/v1/ventes/{codeVente}';
  static readonly findbyId_8Path = '/gestiondestock/v1/ventes/{idVente}';
  static readonly authenticatePath = 'gestiondestock/v1/auth/authenticate';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * @return successful operation
   */
  findAllResponse(): __Observable<__StrictHttpResponse<Array<ArticleDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/articles/all`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<ArticleDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findAll(): __Observable<Array<ArticleDto>> {
    return this.findAllResponse().pipe(
      __map(_r => _r.body as Array<ArticleDto>)
    );
  }

  /**
   * @return successful operation
   */
  saveResponse(): __Observable<__StrictHttpResponse<ArticleDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/articles/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ArticleDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  save(): __Observable<ArticleDto> {
    return this.saveResponse().pipe(
      __map(_r => _r.body as ArticleDto)
    );
  }
  deleteResponse(): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/gestiondestock/v1/articles/delete/${encodeURIComponent(String(idArticle))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<null>;
      })
    );
  }  delete(): __Observable<null> {
    return this.deleteResponse().pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * @return successful operation
   */
  findHistoriqueCommandeClientResponse(): __Observable<__StrictHttpResponse<Array<LigneCommandeClientDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/articles/historique/commandesclient/${encodeURIComponent(String(idArticle))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<LigneCommandeClientDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findHistoriqueCommandeClient(): __Observable<Array<LigneCommandeClientDto>> {
    return this.findHistoriqueCommandeClientResponse().pipe(
      __map(_r => _r.body as Array<LigneCommandeClientDto>)
    );
  }

  /**
   * @return successful operation
   */
  findHistoriqueCommandeFournisseurResponse(): __Observable<__StrictHttpResponse<Array<LigneCommandeFournisseurDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/articles/historique/commandesfournisseur/${encodeURIComponent(String(idArticle))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<LigneCommandeFournisseurDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findHistoriqueCommandeFournisseur(): __Observable<Array<LigneCommandeFournisseurDto>> {
    return this.findHistoriqueCommandeFournisseurResponse().pipe(
      __map(_r => _r.body as Array<LigneCommandeFournisseurDto>)
    );
  }

  /**
   * @return successful operation
   */
  findHistoriqueVentesResponse(): __Observable<__StrictHttpResponse<Array<LigneVenteDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/articles/historique/ventes/${encodeURIComponent(String(idArticle))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<LigneVenteDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findHistoriqueVentes(): __Observable<Array<LigneVenteDto>> {
    return this.findHistoriqueVentesResponse().pipe(
      __map(_r => _r.body as Array<LigneVenteDto>)
    );
  }

  /**
   * @return successful operation
   */
  findbyCodeArticleResponse(): __Observable<__StrictHttpResponse<ArticleDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/articles/${encodeURIComponent(String(codeArticle))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ArticleDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findbyCodeArticle(): __Observable<ArticleDto> {
    return this.findbyCodeArticleResponse().pipe(
      __map(_r => _r.body as ArticleDto)
    );
  }

  /**
   * @return successful operation
   */
  findbyIdResponse(): __Observable<__StrictHttpResponse<ArticleDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/articles/${encodeURIComponent(String(idArticle))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ArticleDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findbyId(): __Observable<ArticleDto> {
    return this.findbyIdResponse().pipe(
      __map(_r => _r.body as ArticleDto)
    );
  }

  /**
   * @return successful operation
   */
  findAllArticlesByIdCategoryResponse(): __Observable<__StrictHttpResponse<Array<ArticleDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/articles/${encodeURIComponent(String(idCategory))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<ArticleDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findAllArticlesByIdCategory(): __Observable<Array<ArticleDto>> {
    return this.findAllArticlesByIdCategoryResponse().pipe(
      __map(_r => _r.body as Array<ArticleDto>)
    );
  }

  /**
   * @return successful operation
   */
  findAll_1Response(): __Observable<__StrictHttpResponse<Array<CategorieDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/categories/all`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<CategorieDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findAll_1(): __Observable<Array<CategorieDto>> {
    return this.findAll_1Response().pipe(
      __map(_r => _r.body as Array<CategorieDto>)
    );
  }

  /**
   * @return successful operation
   */
  save_1Response(): __Observable<__StrictHttpResponse<CategorieDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/categories/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CategorieDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  save_1(): __Observable<CategorieDto> {
    return this.save_1Response().pipe(
      __map(_r => _r.body as CategorieDto)
    );
  }
  delete_1Response(): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/gestiondestock/v1/categories/delete/${encodeURIComponent(String(idCategorie))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<null>;
      })
    );
  }  delete_1(): __Observable<null> {
    return this.delete_1Response().pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * @return successful operation
   */
  findbyCodeResponse(): __Observable<__StrictHttpResponse<CategorieDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/categories/${encodeURIComponent(String(codeCategorie))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CategorieDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findbyCode(): __Observable<CategorieDto> {
    return this.findbyCodeResponse().pipe(
      __map(_r => _r.body as CategorieDto)
    );
  }

  /**
   * @return successful operation
   */
  findbyId_1Response(): __Observable<__StrictHttpResponse<CategorieDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/categories/${encodeURIComponent(String(idCategorie))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CategorieDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findbyId_1(): __Observable<CategorieDto> {
    return this.findbyId_1Response().pipe(
      __map(_r => _r.body as CategorieDto)
    );
  }

  /**
   * @return successful operation
   */
  findAll_2Response(): __Observable<__StrictHttpResponse<Array<ClientDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/clients/all`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<ClientDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findAll_2(): __Observable<Array<ClientDto>> {
    return this.findAll_2Response().pipe(
      __map(_r => _r.body as Array<ClientDto>)
    );
  }

  /**
   * @return successful operation
   */
  save_2Response(): __Observable<__StrictHttpResponse<ClientDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/clients/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ClientDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  save_2(): __Observable<ClientDto> {
    return this.save_2Response().pipe(
      __map(_r => _r.body as ClientDto)
    );
  }
  delete_2Response(): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/gestiondestock/v1/clients/delete/${encodeURIComponent(String(idClient))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<null>;
      })
    );
  }  delete_2(): __Observable<null> {
    return this.delete_2Response().pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * @return successful operation
   */
  findbyId_2Response(): __Observable<__StrictHttpResponse<ClientDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/clients/${encodeURIComponent(String(idClient))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ClientDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findbyId_2(): __Observable<ClientDto> {
    return this.findbyId_2Response().pipe(
      __map(_r => _r.body as ClientDto)
    );
  }

  /**
   * @return successful operation
   */
  findAll_3Response(): __Observable<__StrictHttpResponse<Array<CommandeClientDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/commandesclients/all`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<CommandeClientDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findAll_3(): __Observable<Array<CommandeClientDto>> {
    return this.findAll_3Response().pipe(
      __map(_r => _r.body as Array<CommandeClientDto>)
    );
  }

  /**
   * @return successful operation
   */
  deleteArticleResponse(): __Observable<__StrictHttpResponse<CommandeClientDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/gestiondestock/v1/commandesclients/article/delete/${encodeURIComponent(String(idCommande))}/${encodeURIComponent(String(idLigneCommande))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CommandeClientDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  deleteArticle(): __Observable<CommandeClientDto> {
    return this.deleteArticleResponse().pipe(
      __map(_r => _r.body as CommandeClientDto)
    );
  }

  /**
   * @return successful operation
   */
  updateArticleResponse(): __Observable<__StrictHttpResponse<CommandeClientDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'PATCH',
      this.rootUrl + `/gestiondestock/v1/commandesclients/article/update/${encodeURIComponent(String(idCommande))}/${encodeURIComponent(String(idLigneCommande))}/${encodeURIComponent(String(idArticle))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CommandeClientDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  updateArticle(): __Observable<CommandeClientDto> {
    return this.updateArticleResponse().pipe(
      __map(_r => _r.body as CommandeClientDto)
    );
  }

  /**
   * @return successful operation
   */
  updateClientResponse(): __Observable<__StrictHttpResponse<CommandeClientDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'PATCH',
      this.rootUrl + `/gestiondestock/v1/commandesclients/client/update/${encodeURIComponent(String(idCommande))}/${encodeURIComponent(String(idClient))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CommandeClientDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  updateClient(): __Observable<CommandeClientDto> {
    return this.updateClientResponse().pipe(
      __map(_r => _r.body as CommandeClientDto)
    );
  }

  /**
   * @return successful operation
   */
  save_3Response(): __Observable<__StrictHttpResponse<CommandeClientDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/commandesclients/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CommandeClientDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  save_3(): __Observable<CommandeClientDto> {
    return this.save_3Response().pipe(
      __map(_r => _r.body as CommandeClientDto)
    );
  }

  /**
   * @return successful operation
   */
  delete_3Response(): __Observable<__StrictHttpResponse<{}>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/gestiondestock/v1/commandesclients/delete/${encodeURIComponent(String(idCommandeClient))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<{}>;
      })
    );
  }
  /**
   * @return successful operation
   */
  delete_3(): __Observable<{}> {
    return this.delete_3Response().pipe(
      __map(_r => _r.body as {})
    );
  }

  /**
   * @return successful operation
   */
  updateEtatCommandeResponse(): __Observable<__StrictHttpResponse<CommandeClientDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'PATCH',
      this.rootUrl + `/gestiondestock/v1/commandesclients/etat/update/${encodeURIComponent(String(idCommande))}/${encodeURIComponent(String(etatCommande))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CommandeClientDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  updateEtatCommande(): __Observable<CommandeClientDto> {
    return this.updateEtatCommandeResponse().pipe(
      __map(_r => _r.body as CommandeClientDto)
    );
  }

  /**
   * @return successful operation
   */
  findAllLignesCommandesClientByCommandeClientIdResponse(): __Observable<__StrictHttpResponse<Array<LigneCommandeClientDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/commandesclients/lignescommande/${encodeURIComponent(String(idCommande))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<LigneCommandeClientDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findAllLignesCommandesClientByCommandeClientId(): __Observable<Array<LigneCommandeClientDto>> {
    return this.findAllLignesCommandesClientByCommandeClientIdResponse().pipe(
      __map(_r => _r.body as Array<LigneCommandeClientDto>)
    );
  }

  /**
   * @return successful operation
   */
  updateQuantiteCommandeResponse(): __Observable<__StrictHttpResponse<CommandeClientDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'PATCH',
      this.rootUrl + `/gestiondestock/v1/commandesclients/quantite/update/${encodeURIComponent(String(idCommande))}/${encodeURIComponent(String(idLigneCommande))}/${encodeURIComponent(String(quantite))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CommandeClientDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  updateQuantiteCommande(): __Observable<CommandeClientDto> {
    return this.updateQuantiteCommandeResponse().pipe(
      __map(_r => _r.body as CommandeClientDto)
    );
  }

  /**
   * @return successful operation
   */
  findByCodeResponse(): __Observable<__StrictHttpResponse<CommandeClientDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/commandesclients/${encodeURIComponent(String(codeCommandeClient))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CommandeClientDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findByCode(): __Observable<CommandeClientDto> {
    return this.findByCodeResponse().pipe(
      __map(_r => _r.body as CommandeClientDto)
    );
  }

  /**
   * @return successful operation
   */
  findbyId_3Response(): __Observable<__StrictHttpResponse<CommandeClientDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/commandesclients/${encodeURIComponent(String(idCommandeClient))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CommandeClientDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findbyId_3(): __Observable<CommandeClientDto> {
    return this.findbyId_3Response().pipe(
      __map(_r => _r.body as CommandeClientDto)
    );
  }

  /**
   * @return successful operation
   */
  findAll_4Response(): __Observable<__StrictHttpResponse<Array<CommandeFournisseurDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/commandesfournisseurs/all`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<CommandeFournisseurDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findAll_4(): __Observable<Array<CommandeFournisseurDto>> {
    return this.findAll_4Response().pipe(
      __map(_r => _r.body as Array<CommandeFournisseurDto>)
    );
  }

  /**
   * @return successful operation
   */
  deleteArticle_1Response(): __Observable<__StrictHttpResponse<CommandeFournisseurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/gestiondestock/v1/commandesfournisseurs/article/delete/${encodeURIComponent(String(idCommande))}/${encodeURIComponent(String(idLigneCommande))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CommandeFournisseurDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  deleteArticle_1(): __Observable<CommandeFournisseurDto> {
    return this.deleteArticle_1Response().pipe(
      __map(_r => _r.body as CommandeFournisseurDto)
    );
  }

  /**
   * @return successful operation
   */
  updateArticle_1Response(): __Observable<__StrictHttpResponse<CommandeFournisseurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'PATCH',
      this.rootUrl + `/gestiondestock/v1/commandesfournisseurs/article/update/${encodeURIComponent(String(idCommande))}/${encodeURIComponent(String(idLigneCommande))}/${encodeURIComponent(String(idArticle))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CommandeFournisseurDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  updateArticle_1(): __Observable<CommandeFournisseurDto> {
    return this.updateArticle_1Response().pipe(
      __map(_r => _r.body as CommandeFournisseurDto)
    );
  }

  /**
   * @return successful operation
   */
  updateFournisseurResponse(): __Observable<__StrictHttpResponse<CommandeFournisseurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'PATCH',
      this.rootUrl + `/gestiondestock/v1/commandesfournisseurs/client/update/${encodeURIComponent(String(idCommande))}/${encodeURIComponent(String(idFournisseur))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CommandeFournisseurDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  updateFournisseur(): __Observable<CommandeFournisseurDto> {
    return this.updateFournisseurResponse().pipe(
      __map(_r => _r.body as CommandeFournisseurDto)
    );
  }

  /**
   * @return successful operation
   */
  save_4Response(): __Observable<__StrictHttpResponse<CommandeFournisseurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/commandesfournisseurs/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CommandeFournisseurDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  save_4(): __Observable<CommandeFournisseurDto> {
    return this.save_4Response().pipe(
      __map(_r => _r.body as CommandeFournisseurDto)
    );
  }

  /**
   * @return successful operation
   */
  delete_4Response(): __Observable<__StrictHttpResponse<{}>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/gestiondestock/v1/commandesfournisseurs/delete/${encodeURIComponent(String(idCommandeFournisseur))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<{}>;
      })
    );
  }
  /**
   * @return successful operation
   */
  delete_4(): __Observable<{}> {
    return this.delete_4Response().pipe(
      __map(_r => _r.body as {})
    );
  }

  /**
   * @return successful operation
   */
  updateEtatCommande_1Response(): __Observable<__StrictHttpResponse<CommandeFournisseurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'PATCH',
      this.rootUrl + `/gestiondestock/v1/commandesfournisseurs/etat/update/${encodeURIComponent(String(idCommande))}/${encodeURIComponent(String(etatCommande))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CommandeFournisseurDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  updateEtatCommande_1(): __Observable<CommandeFournisseurDto> {
    return this.updateEtatCommande_1Response().pipe(
      __map(_r => _r.body as CommandeFournisseurDto)
    );
  }

  /**
   * @return successful operation
   */
  findAllLignesCommandesFournisseurByCommandeFournisseurIdResponse(): __Observable<__StrictHttpResponse<Array<LigneCommandeFournisseurDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/commandesfournisseurs/lignescommande/${encodeURIComponent(String(idCommande))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<LigneCommandeFournisseurDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findAllLignesCommandesFournisseurByCommandeFournisseurId(): __Observable<Array<LigneCommandeFournisseurDto>> {
    return this.findAllLignesCommandesFournisseurByCommandeFournisseurIdResponse().pipe(
      __map(_r => _r.body as Array<LigneCommandeFournisseurDto>)
    );
  }

  /**
   * @return successful operation
   */
  updateQuantiteCommande_1Response(): __Observable<__StrictHttpResponse<CommandeFournisseurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'PATCH',
      this.rootUrl + `/gestiondestock/v1/commandesfournisseurs/quantite/update/${encodeURIComponent(String(idCommande))}/${encodeURIComponent(String(idLigneCommande))}/${encodeURIComponent(String(quantite))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CommandeFournisseurDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  updateQuantiteCommande_1(): __Observable<CommandeFournisseurDto> {
    return this.updateQuantiteCommande_1Response().pipe(
      __map(_r => _r.body as CommandeFournisseurDto)
    );
  }

  /**
   * @return successful operation
   */
  findByCode_1Response(): __Observable<__StrictHttpResponse<CommandeFournisseurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/commandesfournisseurs/${encodeURIComponent(String(codeCommandeFournisseur))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CommandeFournisseurDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findByCode_1(): __Observable<CommandeFournisseurDto> {
    return this.findByCode_1Response().pipe(
      __map(_r => _r.body as CommandeFournisseurDto)
    );
  }

  /**
   * @return successful operation
   */
  findbyId_4Response(): __Observable<__StrictHttpResponse<CommandeFournisseurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/commandesfournisseurs/${encodeURIComponent(String(idCommandeFournisseur))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CommandeFournisseurDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findbyId_4(): __Observable<CommandeFournisseurDto> {
    return this.findbyId_4Response().pipe(
      __map(_r => _r.body as CommandeFournisseurDto)
    );
  }

  /**
   * @return successful operation
   */
  findAll_5Response(): __Observable<__StrictHttpResponse<Array<EntrepriseDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/entreprises/all`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<EntrepriseDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findAll_5(): __Observable<Array<EntrepriseDto>> {
    return this.findAll_5Response().pipe(
      __map(_r => _r.body as Array<EntrepriseDto>)
    );
  }

  /**
   * @return successful operation
   */
  save_5Response(): __Observable<__StrictHttpResponse<EntrepriseDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/entreprises/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<EntrepriseDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  save_5(entreprise: EntrepriseDto): Observable<EntrepriseDto> {
    return this.save_5Response().pipe(
      __map(_r => _r.body as EntrepriseDto)
    );
  }
  delete_5Response(): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/gestiondestock/v1/entreprises/delete/${encodeURIComponent(String(idEntreprise))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<null>;
      })
    );
  }  delete_5(): __Observable<null> {
    return this.delete_5Response().pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * @return successful operation
   */
  findbyId_5Response(): __Observable<__StrictHttpResponse<EntrepriseDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/entreprises/${encodeURIComponent(String(idEntreprise))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<EntrepriseDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findbyId_5(): __Observable<EntrepriseDto> {
    return this.findbyId_5Response().pipe(
      __map(_r => _r.body as EntrepriseDto)
    );
  }

  /**
   * @return successful operation
   */
  findAll_6Response(): __Observable<__StrictHttpResponse<Array<FournisseurDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/fournisseurs/all`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<FournisseurDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findAll_6(): __Observable<Array<FournisseurDto>> {
    return this.findAll_6Response().pipe(
      __map(_r => _r.body as Array<FournisseurDto>)
    );
  }

  /**
   * @return successful operation
   */
  save_6Response(): __Observable<__StrictHttpResponse<FournisseurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/fournisseurs/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<FournisseurDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  save_6(): __Observable<FournisseurDto> {
    return this.save_6Response().pipe(
      __map(_r => _r.body as FournisseurDto)
    );
  }
  delete_6Response(): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/gestiondestock/v1/fournisseurs/delete/${encodeURIComponent(String(idFournisseur))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<null>;
      })
    );
  }  delete_6(): __Observable<null> {
    return this.delete_6Response().pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * @return successful operation
   */
  findbyId_6Response(): __Observable<__StrictHttpResponse<FournisseurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/fournisseurs/${encodeURIComponent(String(idFournisseur))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<FournisseurDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findbyId_6(): __Observable<FournisseurDto> {
    return this.findbyId_6Response().pipe(
      __map(_r => _r.body as FournisseurDto)
    );
  }

  /**
   * @return successful operation
   */
  mvtStkArticleResponse(): __Observable<__StrictHttpResponse<Array<MouvementStockDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/mouvementstock/articles/${encodeURIComponent(String(idArticle))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<MouvementStockDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  mvtStkArticle(): __Observable<Array<MouvementStockDto>> {
    return this.mvtStkArticleResponse().pipe(
      __map(_r => _r.body as Array<MouvementStockDto>)
    );
  }

  /**
   * @return successful operation
   */
  correctionStockNegResponse(): __Observable<__StrictHttpResponse<MouvementStockDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/mouvementstock/correctionneg`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<MouvementStockDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  correctionStockNeg(): __Observable<MouvementStockDto> {
    return this.correctionStockNegResponse().pipe(
      __map(_r => _r.body as MouvementStockDto)
    );
  }

  /**
   * @return successful operation
   */
  correctionStockPosResponse(): __Observable<__StrictHttpResponse<MouvementStockDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/mouvementstock/correctionpos`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<MouvementStockDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  correctionStockPos(): __Observable<MouvementStockDto> {
    return this.correctionStockPosResponse().pipe(
      __map(_r => _r.body as MouvementStockDto)
    );
  }

  /**
   * @return successful operation
   */
  entreeStockResponse(): __Observable<__StrictHttpResponse<MouvementStockDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/mouvementstock/entree`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<MouvementStockDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  entreeStock(): __Observable<MouvementStockDto> {
    return this.entreeStockResponse().pipe(
      __map(_r => _r.body as MouvementStockDto)
    );
  }

  /**
   * @return successful operation
   */
  sortieStockResponse(): __Observable<__StrictHttpResponse<MouvementStockDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/mouvementstock/sortie`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<MouvementStockDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  sortieStock(): __Observable<MouvementStockDto> {
    return this.sortieStockResponse().pipe(
      __map(_r => _r.body as MouvementStockDto)
    );
  }

  /**
   * @return successful operation
   */
  stockReelArticleResponse(): __Observable<__StrictHttpResponse<number>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/mouvementstock/stockreel/${encodeURIComponent(String(idArticle))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'text'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return (_r as HttpResponse<any>).clone({ body: parseFloat((_r as HttpResponse<any>).body as string) }) as __StrictHttpResponse<number>
      })
    );
  }
  /**
   * @return successful operation
   */
  stockReelArticle(): __Observable<number> {
    return this.stockReelArticleResponse().pipe(
      __map(_r => _r.body as number)
    );
  }

  /**
   * @return successful operation
   */
  savePhotoResponse(): __Observable<__StrictHttpResponse<{}>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/photos/${encodeURIComponent(String(id))}/${encodeURIComponent(String(title))}/${encodeURIComponent(String(context))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<{}>;
      })
    );
  }
  /**
   * @return successful operation
   */
  savePhoto(): __Observable<{}> {
    return this.savePhotoResponse().pipe(
      __map(_r => _r.body as {})
    );
  }

  /**
   * @return successful operation
   */
  findAll_7Response(): __Observable<__StrictHttpResponse<Array<UtilisateurDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/utilisateurs/all`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<UtilisateurDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findAll_7(): __Observable<Array<UtilisateurDto>> {
    return this.findAll_7Response().pipe(
      __map(_r => _r.body as Array<UtilisateurDto>)
    );
  }

  /**
   * @return successful operation
   */
  changerMotDePasseResponse(): __Observable<__StrictHttpResponse<UtilisateurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/utilisateurs/changepassword`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UtilisateurDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  changerMotDePasse(): __Observable<UtilisateurDto> {
    return this.changerMotDePasseResponse().pipe(
      __map(_r => _r.body as UtilisateurDto)
    );
  }

  /**
   * @return successful operation
   */
  save_7Response(): __Observable<__StrictHttpResponse<UtilisateurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/utilisateurs/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UtilisateurDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  save_7(): __Observable<UtilisateurDto> {
    return this.save_7Response().pipe(
      __map(_r => _r.body as UtilisateurDto)
    );
  }
  delete_7Response(): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/gestiondestock/v1/utilisateurs/delete/${encodeURIComponent(String(idUtilisateur))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<null>;
      })
    );
  }  delete_7(): __Observable<null> {
    return this.delete_7Response().pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * @return successful operation
   */
  findByEmailResponse(): __Observable<__StrictHttpResponse<UtilisateurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/utilisateurs/${encodeURIComponent(String(email))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UtilisateurDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findByEmail(): __Observable<UtilisateurDto> {
    return this.findByEmailResponse().pipe(
      __map(_r => _r.body as UtilisateurDto)
    );
  }

  /**
   * @return successful operation
   */
  findbyId_7Response(): __Observable<__StrictHttpResponse<UtilisateurDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/utilisateurs/${encodeURIComponent(String(idUtilisateur))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UtilisateurDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findbyId_7(): __Observable<UtilisateurDto> {
    return this.findbyId_7Response().pipe(
      __map(_r => _r.body as UtilisateurDto)
    );
  }

  /**
   * @return successful operation
   */
  findAll_8Response(): __Observable<__StrictHttpResponse<Array<VenteDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/ventes/all`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<VenteDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findAll_8(): __Observable<Array<VenteDto>> {
    return this.findAll_8Response().pipe(
      __map(_r => _r.body as Array<VenteDto>)
    );
  }

  /**
   * @return successful operation
   */
  save_8Response(): __Observable<__StrictHttpResponse<VenteDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/ventes/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<VenteDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  save_8(): __Observable<VenteDto> {
    return this.save_8Response().pipe(
      __map(_r => _r.body as VenteDto)
    );
  }
  delete_8Response(): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/gestiondestock/v1/ventes/delete/${encodeURIComponent(String(idVente))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<null>;
      })
    );
  }  delete_8(): __Observable<null> {
    return this.delete_8Response().pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * @return successful operation
   */
  findbyCode_1Response(): __Observable<__StrictHttpResponse<VenteDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/ventes/${encodeURIComponent(String(codeVente))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<VenteDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findbyCode_1(): __Observable<VenteDto> {
    return this.findbyCode_1Response().pipe(
      __map(_r => _r.body as VenteDto)
    );
  }

  /**
   * @return successful operation
   */
  findbyId_8Response(): __Observable<__StrictHttpResponse<VenteDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/ventes/${encodeURIComponent(String(idVente))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<VenteDto>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findbyId_8(): __Observable<VenteDto> {
    return this.findbyId_8Response().pipe(
      __map(_r => _r.body as VenteDto)
    );
  }

  /**
   * @param body undefined
   * @return successful operation
   */
  authenticateResponse(body?: AuthenticationRequest): __Observable<__StrictHttpResponse<AuthenticationResponse>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = body;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `gestiondestock/v1/auth/authenticate`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<AuthenticationResponse>;
      })
    );
  }
  /**
   * @param body undefined
   * @return successful operation
   */
  authenticate(body?: AuthenticationRequest): __Observable<AuthenticationResponse> {
    return this.authenticateResponse(body).pipe(
      __map(_r => _r.body as AuthenticationResponse)
    );
  }
}

module ApiService {
}

export { ApiService }
