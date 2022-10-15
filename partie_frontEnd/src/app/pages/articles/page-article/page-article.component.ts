import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {ArticleDto} from "../../../../gs-api/src/models/article-dto";
import {ArticleService} from "../../../services/article/article.service";

@Component({
  selector: 'app-page-article',
  templateUrl: './page-article.component.html',
  styleUrls: ['./page-article.component.css']
})
export class PageArticleComponent implements OnInit {
  listArticle: Array<ArticleDto> = [];
  errorMsgs = '';

  constructor(
    private router: Router,
    private articleService: ArticleService
  ) { }

  ngOnInit(): void {
    this.findListArticle()
  }

  findListArticle() : void {
    this.articleService.findAllArticles()
      .subscribe(articles => {
        this.listArticle = articles
      })
  }
  nouvelArticle() {
    this.router.navigate(['nouvelarticle'])
  }

  handleSupprission(event: any) : void {
    if (event === 'success') {
      this.findListArticle()
    } else {
      this.errorMsgs = event
    }
  }
}
