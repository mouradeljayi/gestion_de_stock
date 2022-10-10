import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {CategorieDto} from "../../../../gs-api/src/models/categorie-dto";
import {CategoryService} from "../../../services/category/category.service";

@Component({
  selector: 'app-page-category',
  templateUrl: './page-category.component.html',
  styleUrls: ['./page-category.component.css']
})
export class PageCategoryComponent implements OnInit {

  listCategories : Array<CategorieDto> =  []
  selectedCategoryIdToDelete? = -1
  errorMsg = ''

  constructor(
    private router: Router,
    private categoryService: CategoryService
  ) { }

  ngOnInit(): void {
    this.findAllCategories()
  }

  findAllCategories() : void {
    this.categoryService.findAll()
      .subscribe(res => {
        this.listCategories = res
      })
  }

  nouvelleCategory() {
    this.router.navigate(['nouvellecategory'])
  }

  modifierCategory(id: number) : void {
    this.router.navigate(['nouvellecategory', id])
  }

  confirmerEtSupprimerCategorie(): void {
    if (this.selectedCategoryIdToDelete !== -1) {
      this.categoryService.delete(this.selectedCategoryIdToDelete)
        .subscribe(res => {
          this.findAllCategories()
        }, error => {
          this.errorMsg = error.error.message
        })
    }
  }

  annulerSupprissionCategorie(): void {
    this.selectedCategoryIdToDelete = -1
  }

  selectCategoriePourSupprimer(id?: number): void {
    this.selectedCategoryIdToDelete = id
  }
}
