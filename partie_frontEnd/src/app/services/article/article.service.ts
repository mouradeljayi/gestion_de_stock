import { Injectable } from '@angular/core';
import {UserService} from "../user/user.service";
import {ApiService} from "../../../gs-api/src/services/api.service";
import {ArticleDto} from "../../../gs-api/src/models/article-dto";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  constructor(
    private userService: UserService,
    private apiService: ApiService
  ) { }

  enregistrerArticle(articleDto: ArticleDto) : Observable<ArticleDto> {
    articleDto.idEntreprise = this.userService.getConnectedUser().entreprise?.id
    return this.apiService.save(articleDto)
  }

  findAllArticles() : Observable<ArticleDto[]> {
    return this.apiService.findAll();
  }

  findArticleById(idArticle?: number) : Observable<ArticleDto> {
    if (idArticle) {
      return this.apiService.findbyId(idArticle)
    }
    return of();
  }
}
