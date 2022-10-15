import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ArticleDto} from "../../../../gs-api/src/models/article-dto";
import {Router} from "@angular/router";
import {ArticleService} from "../../../services/article/article.service";

@Component({
  selector: 'app-detail-article',
  templateUrl: './detail-article.component.html',
  styleUrls: ['./detail-article.component.css']
})
export class DetailArticleComponent implements OnInit {

  @Input()
  articleDto: ArticleDto = {}
  @Output()
  supprissionResult = new EventEmitter()

  selectedIdArticleToDelete? = -1

  constructor(
    private router: Router,
    private articleService: ArticleService
  ) { }

  ngOnInit(): void {
  }

  modifierArticle() {
    this.router.navigate(["nouvelarticle", this.articleDto.id])
  }

  confirmerEtSupprimerArticle() : void {
    if (this.articleDto.id) {
      this.articleService.deleteArticle(this.articleDto.id)
        .subscribe(res => {
          this.supprissionResult.emit('success')
        }, error => {
          this.supprissionResult.emit(error.error.error)
        })
    }
  }

}
