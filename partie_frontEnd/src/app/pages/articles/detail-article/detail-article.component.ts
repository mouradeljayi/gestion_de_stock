import {Component, Input, OnInit} from '@angular/core';
import {ArticleDto} from "../../../../gs-api/src/models/article-dto";
import {Router} from "@angular/router";

@Component({
  selector: 'app-detail-article',
  templateUrl: './detail-article.component.html',
  styleUrls: ['./detail-article.component.css']
})
export class DetailArticleComponent implements OnInit {

  @Input()
  articleDto: ArticleDto = {}

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  modifierArticle() {
    this.router.navigate(["nouvelarticle", this.articleDto.id])
  }
}
