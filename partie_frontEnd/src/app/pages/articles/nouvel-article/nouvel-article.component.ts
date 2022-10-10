import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ArticleService} from "../../../services/article/article.service";
import {ArticleDto} from "../../../../gs-api/src/models/article-dto";
import {CategorieDto} from "../../../../gs-api/src/models/categorie-dto";
import {CategoryService} from "../../../services/category/category.service";

@Component({
  selector: 'app-nouvel-article',
  templateUrl: './nouvel-article.component.html',
  styleUrls: ['./nouvel-article.component.css']
})
export class NouvelArticleComponent implements OnInit {

  articleDto: ArticleDto =  {}
  categorieDto: CategorieDto =  {}
  listCategories: Array<CategorieDto> =  []
  errorMsg: Array<string> =  []

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private articleService: ArticleService,
    private categorieService: CategoryService
  ) { }

  ngOnInit(): void {
    this.categorieService.findAll()
      .subscribe(categories => {
        this.listCategories = categories
      })

    const idArticle = this.activatedRoute.snapshot.params['idArticle']
    if (idArticle) {
      this.articleService.findArticleById(idArticle)
        .subscribe(article => {
          this.articleDto = article
          this.categorieDto = this.articleDto.categorie ?
                              this.articleDto.categorie : {}
        })
    }
  }

  cancel() {
    this.router.navigate(['articles'])
  }

  enregistrerArticle(): void {
    this.articleDto.categorie = this.categorieDto
    this.articleService.enregistrerArticle(this.articleDto)
      .subscribe(art => {
        this.router.navigate(['articles'])
      }, error => {
        this.errorMsg = error.error.errros
      })
  }
}
