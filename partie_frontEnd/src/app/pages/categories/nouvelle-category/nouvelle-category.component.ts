import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CategorieDto} from "../../../../gs-api/src/models/categorie-dto";
import {CategoryService} from "../../../services/category/category.service";

@Component({
  selector: 'app-nouvelle-category',
  templateUrl: './nouvelle-category.component.html',
  styleUrls: ['./nouvelle-category.component.css']
})
export class NouvelleCategoryComponent implements OnInit {

  categoryDto : CategorieDto = {}
  errorMsg :  Array<string> =  []

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private categorieService: CategoryService
  ) { }

  ngOnInit(): void {

    const idCategory = this.activatedRoute.snapshot.params['idCategory']
      if (idCategory) {
        this.categorieService.findById(idCategory)
          .subscribe(cat => {
            this.categoryDto = cat
          })
      }
  }

  cancel() {
    this.router.navigate(['categories'])
  }

  enregistrerCategorie() : void {
    this.categorieService.enregistrerCategory(this.categoryDto)
      .subscribe(res => {
        this.router.navigate(['categories'])
      }, error => {
        this.errorMsg = error.error.errors
      })
  }
}
