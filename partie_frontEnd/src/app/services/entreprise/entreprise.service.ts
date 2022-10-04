import { Injectable } from '@angular/core';
import {ApiService} from "../../../gs-api/src/services/api.service";
import {EntrepriseDto} from "../../../gs-api/src/models/entreprise-dto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EntrepriseService {

  constructor(
    private service: ApiService
  ) { }

  register(entreprise: EntrepriseDto): Observable<EntrepriseDto>{
    return this.service.save_5(entreprise)
  }
}
