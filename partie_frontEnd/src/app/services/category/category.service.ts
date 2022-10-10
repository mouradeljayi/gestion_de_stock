import { Injectable } from '@angular/core';
import {UserService} from "../user/user.service";
import {ApiService} from "../../../gs-api/src/services/api.service";
import {CategorieDto} from "../../../gs-api/src/models/categorie-dto";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(
    private userService: UserService,
    private service: ApiService
  ) { }

  enregistrerCategory(categorieDto: CategorieDto) : Observable<CategorieDto> {
    categorieDto.idEntreprise = this.userService.getConnectedUser().entreprise?.id
    return this.service.save_1(categorieDto)
  }

  findAll() : Observable<CategorieDto[]> {
    return this.service.findAll_1();
  }

  findById(idCategory: number) : Observable<CategorieDto>{
    return this.service.findbyId_1(idCategory)
  }

  delete(idCategory?: number): Observable<any> {
    if (idCategory) {
      return this.service.delete_1(idCategory)
    }
    return of()
  }
}
